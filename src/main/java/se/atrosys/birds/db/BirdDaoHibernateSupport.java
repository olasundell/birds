package se.atrosys.birds.db;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateAccessor;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Ordinary Hibernate DAO class.
 */
public abstract class BirdDaoHibernateSupport extends HibernateDaoSupport {
	public void shutdown() {
		org.hibernate.classic.Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.createSQLQuery("SHUTDOWN").executeUpdate();
		session.close();
	}

	@Autowired
    public void init( SessionFactory sessionFactory ) {
        setSessionFactory( sessionFactory );
		getHibernateTemplate().setFlushMode(HibernateAccessor.FLUSH_ALWAYS);
    }
}
