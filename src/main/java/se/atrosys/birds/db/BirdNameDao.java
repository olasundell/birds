package se.atrosys.birds.db;

import se.atrosys.birds.model.BirdNameModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/9/11
 * Time: 10:31 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BirdNameDao {
	public BirdNameModel findById(String id);
	public List<BirdNameModel> findAll();
	public void save(BirdNameModel model);
	public void update(BirdNameModel model);
	public void delete(BirdNameModel model);
	public void shutdown();
}
