package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.atrosys.birds.db.GroupDao;
import se.atrosys.birds.model.GroupModel;

import java.util.List;

/**
 * TODO write comment
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService {
	@Autowired GroupDao dao;

	public GroupModel findById(String id) {
		return dao.findById(id);
	}

	public List<GroupModel> findAll() {
		return dao.findAll();
	}

	public void save(GroupModel model) {
		dao.save(model);
	}

	public void update(GroupModel model) {
		dao.update(model);
	}

	public void delete(GroupModel model) {
		dao.delete(model);
	}

	public void shutdown() {
		dao.shutdown();
	}
}
