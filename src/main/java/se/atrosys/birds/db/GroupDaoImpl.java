package se.atrosys.birds.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.FamilyModel;
import se.atrosys.birds.model.GroupModel;

import java.util.List;

/**
 * TODO write comment
 */
@Repository("groupDao")
public class GroupDaoImpl extends BirdDaoHibernateSupport implements GroupDao {
	public GroupModel findById(String id) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Session session = hibernateTemplate.getSessionFactory().openSession();

		GroupModel model = (GroupModel) session.get(GroupModel.class, id);

		if (model != null) {
			for (FamilyModel familyModel: model.getFamilies()) {
				familyModel.getFamily();
				familyModel.getBirds();
			}
		}

		session.close();

		return model;
	}

	public List<GroupModel> findAll() {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Session session = hibernateTemplate.getSessionFactory().openSession();

		String hql = "from se.atrosys.birds.model.GroupModel";
		Query query = session.createQuery(hql);
		List<GroupModel> list = query.list();
		
		for (GroupModel groupModel: list) {
			for (FamilyModel model: groupModel.getFamilies()) {
				for (BirdModel birdModel: model.getBirds()) {
					birdModel.getId();
				}
			}
		}

		session.close();

		return list;
	}

	public void save(GroupModel model) {
		getHibernateTemplate().save(model);
	}

	public void update(GroupModel model) {
		getHibernateTemplate().update(model);
	}

	public void delete(GroupModel model) {
		getHibernateTemplate().delete(model);
	}

	@Autowired
	public void init( SessionFactory sessionFactory ) {
		super.init(sessionFactory);
	}
}
