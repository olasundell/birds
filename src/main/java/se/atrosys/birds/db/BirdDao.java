package se.atrosys.birds.db;

import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.model.BirdModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 8:54 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BirdDao {
	public BirdModel findById(String id);
	public List<BirdModel> findAll();
	public void save(BirdModel model) throws NoSuchLanguageException;
	public void update(BirdModel model);
	public void delete(BirdModel model);
	public void shutdown();
	public void deleteAll();

	BirdModel getRandomBird();

	BirdModel findByScientificName(String name);

    void clearAll();
}
