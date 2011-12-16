package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.atrosys.birds.db.FamilyDao;
import se.atrosys.birds.model.FamilyModel;

import java.util.List;

/**
 * TODO write comment
 */
@Service("familyService")
public class FamilyServiceImpl implements FamilyService {
	@Autowired FamilyDao dao;
	@Autowired GroupService groupService;
	
	public FamilyModel findById(String id) {
		return dao.findById(id);
	}

	public List<FamilyModel> findAll() {
		return dao.findAll();
	}

	public void save(FamilyModel model) {
		if (groupService.findById(model.getGroup().getGroupName()) == null) {
			groupService.save(model.getGroup());
		}

		dao.save(model);
	}

	public void update(FamilyModel model) {
		dao.update(model);
	}

	public void delete(FamilyModel model) {
		dao.delete(model);
	}

	public void shutdown() {
		dao.shutdown();
	}
}
