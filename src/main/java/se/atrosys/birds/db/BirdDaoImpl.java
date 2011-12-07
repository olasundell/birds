package se.atrosys.birds.db;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import se.atrosys.birds.model.BirdModel;

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
	public BirdModel findById(String id) {
		return (BirdModel)getHibernateTemplate().get(BirdModel.class, id);
	}

	public List<BirdModel> findAll() {
		return getHibernateTemplate().find("from se.atrosys.birds.model.BirdModel");
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

    @Autowired
    public void init( SessionFactory sessionFactory ) {
        setSessionFactory( sessionFactory );
    }
}
