package se.atrosys.birds.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.FamilyModel;

import java.util.List;

/**
 * TODO write comment
 */
@Repository("familyDao")
public class FamilyDaoImpl extends HibernateDaoSupport implements FamilyDao {
	public FamilyModel findById(String id) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Session session = hibernateTemplate.getSessionFactory().openSession();

		FamilyModel model = (FamilyModel) session.get(FamilyModel.class, id);

		if (model != null) {
			for (BirdModel birdModel: model.getBirds()) {
				birdModel.getId();
			}
		}

		session.close();

		return model;
	}

	public List<FamilyModel> findAll() {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Session session = hibernateTemplate.getSessionFactory().openSession();

		String hql = "from se.atrosys.birds.model.FamilyModel";
		Query query = session.createQuery(hql);
		List<FamilyModel> list = query.list();

		for (FamilyModel model: list) {
			for (BirdModel birdModel: model.getBirds()) {
				birdModel.getId();
			}
		}

		session.close();

		return list;
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
