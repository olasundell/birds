package se.atrosys.birds.service;

import org.springframework.stereotype.Service;
import se.atrosys.birds.model.BirdPhotoModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/11/11
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BirdPhotoService {
	public BirdPhotoModel findById(String id);
	public List<BirdPhotoModel> findAll();
	public void save(BirdPhotoModel model);
	public void update(BirdPhotoModel model);
	public void delete(BirdPhotoModel model);
	public void saveAll(List<BirdPhotoModel> birdModels);
	public void shutdown();
}
