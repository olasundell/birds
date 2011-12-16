package se.atrosys.birds.service;

import se.atrosys.birds.model.GroupModel;

import java.util.List;

/**
 * TODO write comment
 */
public interface GroupService {
	public GroupModel findById(String id);
	public List<GroupModel> findAll();
	public void save(GroupModel model);
	public void update(GroupModel model);
	public void delete(GroupModel model);
	public void shutdown();
}
