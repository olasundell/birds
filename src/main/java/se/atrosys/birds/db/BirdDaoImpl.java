package se.atrosys.birds.db;

import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.model.*;
import se.atrosys.birds.service.LanguageService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository("birdDao")
public class BirdDaoImpl extends HibernateDaoSupport implements BirdDao {
	@Autowired BirdPhotoDao birdPhotoDao;
	@Autowired LanguageService languageService;
	
	public BirdModel findById(String id) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Session session = hibernateTemplate.getSessionFactory().openSession();

		BirdModel model = (BirdModel) session.get(BirdModel.class, id);

		retrieveLazyBindings(model);

		session.close();

		return model;
	}

	public List<BirdModel> findAll() {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Session session = hibernateTemplate.getSessionFactory().openSession();

		String hql = "from se.atrosys.birds.model.BirdModel";
		Query query = session.createQuery(hql);
		List<BirdModel> list = query.list();
		
		for (BirdModel model: list) {
			retrieveLazyBindings(model);
		}

		session.close();

		return list;
	}

	public void save(BirdModel model) throws NoSuchLanguageException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.save(model);

		Set<RegionModel> regionModelSet = new HashSet<RegionModel>();

		for (RegionalScarcityModel regionalScarcityModel: model.getRegionalScarcity()){
			regionModelSet.add(regionalScarcityModel.getRegion());
		}
		
		for (RegionModel regionModel: regionModelSet) {
			Query query = session.createQuery("from se.atrosys.birds.model.RegionModel where name = ?");
			query.setText(0, regionModel.getName());
			if (query.list().isEmpty()) {
				session.save(regionModel);
			}
		}

		for (RegionalScarcityModel regionalScarcityModel: model.getRegionalScarcity()){
			session.save(regionalScarcityModel);
		}
		
		Query languageQuery = session.createQuery("from se.atrosys.birds.model.LanguageModel where language = ?");
		
		for (BirdNameModel birdNameModel: model.getNames()) {
			if (!languageService.isLanguage(birdNameModel.getLang())) {
				throw new NoSuchLanguageException(String.format("Could not find language %s", birdNameModel.getLang().getLanguage()));
			}

			languageQuery.setText(0, birdNameModel.getLang().getLanguage());
			if (languageQuery.list().isEmpty()) {
//				Session langSession = getHibernateTemplate().getSessionFactory().openSession();
//				Transaction langTransaction = langSession.beginTransaction();

				session.save(birdNameModel.getLang());

//				langTransaction.commit();
//				langSession.close();
			}
			session.save(birdNameModel);
		}

		transaction.commit();
		session.close();
	}

	public void update(BirdModel model) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.update(model);

		Set<RegionModel> regionModelSet = new HashSet<RegionModel>();

		for (RegionalScarcityModel regionalScarcityModel: model.getRegionalScarcity()){
			regionModelSet.add(regionalScarcityModel.getRegion());
		}

		for (RegionModel regionModel: regionModelSet) {
			Query query = session.createQuery("from se.atrosys.birds.model.RegionModel where name = ?");
			query.setText(0, regionModel.getName());
			if (query.list().isEmpty()) {
				session.save(regionModel);
			}
		}

		for (RegionalScarcityModel regionalScarcityModel: model.getRegionalScarcity()){
			session.save(regionalScarcityModel);
		}
		
		Query birdNameQuery = session.createQuery("from se.atrosys.birds.model.BirdNameModel where bird_id = ? and bird_name = ? and language = ?");

		for (BirdNameModel birdNameModel: model.getNames()) {
			if (birdNameModel.getBirdId() == null || birdNameModel.getBirdId().equals(model.getId())) {
				birdNameModel.setBirdId(model.getId());
			}
			
			birdNameQuery.setString(0, model.getId());
			birdNameQuery.setString(1, birdNameModel.getName());
			birdNameQuery.setString(2, birdNameModel.getLang().getLanguage());

			if (birdNameQuery.list().isEmpty()) {
				session.save(birdNameModel);
			}

			logger.info(String.format("Bird name %s already exists with language %s for bird %s", birdNameModel.getName(), birdNameModel.getLang().getLanguage(), model.getScientificName()));
		}

		transaction.commit();
		session.close();
	}

	public void delete(BirdModel model) {
		getHibernateTemplate().delete(model);
	}

	public void shutdown() {
		getHibernateTemplate().getSessionFactory().openSession().createSQLQuery("SHUTDOWN").executeUpdate();
	}

	// another prime example as to why SQL is peerless.
	public BirdModel getRandomBird() {
		String sql = "select id from birds limit 1 offset (select floor(count(*) * random()) from birds)";
		BirdModel model = null;

		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		List list = sqlQuery.list();
		if (!list.isEmpty()) {
			model = findById((String) list.get(0));
		}
		
		session.close();

		return model;
	}

	public BirdModel findByScientificName(String name) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Query query = session.createQuery("from se.atrosys.birds.model.BirdModel where scientificName = ?");
		query.setText(0, name);
		List<BirdModel> list = (List<BirdModel>)query.list();
		if (list.isEmpty()) {
			return null;
		}
		
		BirdModel model = (BirdModel) list.get(0);

		retrieveLazyBindings(model);

		session.close();

		return model;
	}

    @Override
    public void clearAll() {
        getHibernateTemplate().getSessionFactory().close();
/*
        Session session = getHibernateTemplate().getSessionFactory().openSession();

        Transaction transaction = session.getTransaction();
        transaction.begin();

        session.createSQLQuery("delete from BIRDS_PHOTOS_JT").executeUpdate();
        session.createSQLQuery("delete from BIRDS_REGIONAL_SCARCITY").executeUpdate();
        session.createSQLQuery("delete from REGIONAL_SCARCITY").executeUpdate();
        session.createSQLQuery("delete from BIRDS_SOUNDS").executeUpdate();
        session.createSQLQuery("delete from BIRD_NAMES").executeUpdate();
        session.createSQLQuery("delete from BIRDS").executeUpdate();
        session.createSQLQuery("delete from LANGUAGES").executeUpdate();

        transaction.commit();
        session.close();*/
    }

    private void retrieveLazyBindings(BirdModel model) {
		if (model != null) {
			retrieveBirdModelLazyBindings(model);
			for (BirdModel birdModel: model.getFamily().getBirds()) {
				retrieveBirdModelLazyBindings(birdModel);
			}
		}
	}

	private void retrieveBirdModelLazyBindings(BirdModel model) {
		for (BirdPhotoModel photoModel: model.getPhotos()) {
			photoModel.getFarm();
		}

		for (BirdNameModel birdNameModel: model.getNames()) {
			birdNameModel.getLang();
		}

		for (SoundModel soundModel: model.getSounds()) {
			soundModel.getURL();
		}

		for (RegionalScarcityModel regionalScarcityModel:  model.getRegionalScarcity()) {
			regionalScarcityModel.getRegion();
		}
	}

	@Autowired
    public void init( SessionFactory sessionFactory ) {
        setSessionFactory( sessionFactory );
    }
}
