package se.atrosys.birds.db;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import se.atrosys.birds.model.MediaModel;
import se.atrosys.birds.model.SoundModel;

/**
 * TODO write comment
 */
@Repository("soundDao")
public class SoundDaoImpl extends HibernateDaoSupport implements SoundDao {
	@Override
	public void update(SoundModel model) {
		getHibernateTemplate().update(model);
	}

	@Override
	public SoundModel findById(String mediaId) {
		return getHibernateTemplate().get(SoundModel.class, Integer.valueOf(mediaId));
	}

	@Autowired
	public void init( SessionFactory sessionFactory ) {
		setSessionFactory( sessionFactory );
	}
}
