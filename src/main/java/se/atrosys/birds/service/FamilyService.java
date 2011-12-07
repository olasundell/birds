package se.atrosys.birds.service;

import se.atrosys.birds.model.FamilyModel;

import java.util.List;

/**
 * TODO write comment
 */
public interface FamilyService {
	public FamilyModel findById(String id);
	public List<FamilyModel> findAll();
	public void save(FamilyModel model);
	public void update(FamilyModel model);
	public void delete(FamilyModel model);
	public void shutdown();
}
