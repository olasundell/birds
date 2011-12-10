package se.atrosys.birds.service;

import se.atrosys.birds.model.BirdNameModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/10/11
 * Time: 9:55 AM
 * To change this template use File | Settings | File Templates.
 */
public interface BirdNameService {
	public BirdNameModel findById(String id);
	public List<BirdNameModel> findAll();
	public void save(BirdNameModel model);
	public void update(BirdNameModel model);
	public void delete(BirdNameModel model);
	public void saveAll(List<BirdNameModel> birdModels);
	public void shutdown();
}
