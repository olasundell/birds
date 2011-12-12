package se.atrosys.birds.db;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.BirdPhotoModel;

import java.util.List;

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
	public BirdModel findById(String id) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Session session = hibernateTemplate.getSessionFactory().openSession();

		BirdModel model = (BirdModel) session.get(BirdModel.class, id);

		if (model != null) {
			for (BirdPhotoModel photoModel: model.getPhotos()) {
				photoModel.getFarm();
			}

			for (BirdModel birdModel: model.getFamily().getBirds()) {
				birdModel.getId();
			}
		}

		session.close();

		return model;

//		return (BirdModel)getHibernateTemplate().get(BirdModel.class, id);
	}

	public List<BirdModel> findAll() {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Session session = hibernateTemplate.getSessionFactory().openSession();

		String hql = "from se.atrosys.birds.model.BirdModel";
		Query query = session.createQuery(hql);
		List<BirdModel> list = query.list();
		
//		List<BirdModel> list = hibernateTemplate.find();
		
//		List<BirdModel> list = session.("from se.atrosys.birds.model.BirdModel");

		for (BirdModel model: list) {
			for (BirdPhotoModel photo: model.getPhotos()) {
				photo.getFarm();
			}
			
			for (BirdModel birdModel: model.getFamily().getBirds()) {
				birdModel.getId();
			}
		}

		session.close();

		return list;
	}

	public void save(BirdModel model) {
		getHibernateTemplate().save(model);
	}

	public void update(BirdModel model) {
		getHibernateTemplate().update(model);
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

		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		String id = (String) sqlQuery.list().get(0);

		return findById(id);
	}

	@Autowired
    public void init( SessionFactory sessionFactory ) {
        setSessionFactory( sessionFactory );
    }
}
