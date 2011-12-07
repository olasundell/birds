package se.atrosys.birds.db;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import se.atrosys.birds.model.FamilyModel;

import java.util.List;

/**
 * TODO write comment
 */
@Repository("familyDao")
public class FamilyDaoImpl extends HibernateDaoSupport implements FamilyDao {
	public FamilyModel findById(String id) {
		return (FamilyModel)getHibernateTemplate().get(FamilyModel.class, id);
	}

	public List<FamilyModel> findAll() {
		return getHibernateTemplate().find("from se.atrosys.birds.model.FamilyModel");
	}

	public void save(FamilyModel model) {
		getHibernateTemplate().save(model);
	}

	public void update(FamilyModel model) {
		getHibernateTemplate().update(model);
	}

	public void delete(FamilyModel model) {
		getHibernateTemplate().delete(model);
	}

	public void shutdown() {
		getHibernateTemplate().getSessionFactory().openSession().createSQLQuery("SHUTDOWN").executeUpdate();
	}

	@Autowired
	public void init( SessionFactory sessionFactory ) {
		setSessionFactory(sessionFactory);
	}
}
