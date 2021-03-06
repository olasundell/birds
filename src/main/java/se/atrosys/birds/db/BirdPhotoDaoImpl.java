package se.atrosys.birds.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.BirdPhotoModel;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/11/11
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("birdPhotoDao")
public class BirdPhotoDaoImpl extends BirdDaoHibernateSupport implements BirdPhotoDao {
	public BirdPhotoModel findById(String id) {
		return getHibernateTemplate().get(BirdPhotoModel.class, id);
	}

	public List<BirdPhotoModel> findAll() {
		return getHibernateTemplate().find("from se.atrosys.birds.model.BirdPhotoModel");
	}

	public void save(BirdPhotoModel model) {
		getHibernateTemplate().save(model);
	}

	public void update(BirdPhotoModel model) {
		getHibernateTemplate().update(model);
	}

	public void delete(BirdPhotoModel model) {
		getHibernateTemplate().delete(model);
	}

	public void shutdown() {
		getHibernateTemplate().getSessionFactory().openSession().createSQLQuery("SHUTDOWN").executeUpdate();
	}

	public List<BirdPhotoModel> findAllForBird(BirdModel model) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

    @Override
    public List<BirdPhotoModel> findAllIneligible() {
        Session session = getSession();

        Query query = session.createQuery("from se.atrosys.birds.model.BirdPhotoModel where eligible = FALSE");
//        query.setBoolean(0, false);
//        query.setInteger(0, 0);
        List<BirdPhotoModel> list = query.list();

        session.close();

        return list;
    }

/*	public List<BirdPhotoModel> findAllForBird(BirdModel model) {
		get
	}*/

	@Autowired
	public void init( SessionFactory sessionFactory ) {
		super.init(sessionFactory);
	}
}
