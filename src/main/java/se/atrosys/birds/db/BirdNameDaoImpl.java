package se.atrosys.birds.db;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import se.atrosys.birds.model.BirdNameModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/9/11
 * Time: 10:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("birdNameDao")
public class BirdNameDaoImpl extends HibernateDaoSupport implements BirdNameDao {
	public BirdNameModel findById(String id) {
		return getHibernateTemplate().get(BirdNameModel.class, id);
	}

	public List<BirdNameModel> findAll() {
		return getHibernateTemplate().find("from se.atrosys.birds.model.BirdNameModel");
	}

	public void save(BirdNameModel model) {
		getHibernateTemplate().save(model);
	}

	public void update(BirdNameModel model) {
		getHibernateTemplate().update(model);
	}

	public void delete(BirdNameModel model) {
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
