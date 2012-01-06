package se.atrosys.birds.service;

import se.atrosys.birds.exception.NoSuchLanguageException;
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
	public void save(BirdModel model) throws NoSuchLanguageException;
	public void update(BirdModel model);
	public void delete(BirdModel model);
	public void saveAll(List<BirdModel> birdModels) throws NoSuchLanguageException;
	public void shutdown();
	public BirdModel getRandomBird();
	public BirdModel findByScientificName(String choice);
}
