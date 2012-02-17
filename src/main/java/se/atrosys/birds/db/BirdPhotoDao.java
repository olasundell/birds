package se.atrosys.birds.db;

import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.BirdPhotoModel;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/11/11
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BirdPhotoDao {
	public BirdPhotoModel findById(String id);
	public List<BirdPhotoModel> findAll();
	public void save(BirdPhotoModel model);
	public void update(BirdPhotoModel model);
	public void delete(BirdPhotoModel model);
	public void shutdown();
	List<BirdPhotoModel> findAllForBird(BirdModel model);

    List<BirdPhotoModel> findAllIneligible();
}
