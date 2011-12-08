package se.atrosys.birds.service;

import se.atrosys.birds.model.BirdModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BirdService {
	public BirdModel findById(String id);
	public List<BirdModel> findAll();
	public void save(BirdModel model);
	public void update(BirdModel model);
	public void delete(BirdModel model);
	public void saveAll(List<BirdModel> birdModels);
	public void shutdown();
}
