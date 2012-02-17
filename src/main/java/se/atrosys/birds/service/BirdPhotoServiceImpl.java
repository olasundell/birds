package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.atrosys.birds.db.BirdPhotoDao;
import se.atrosys.birds.model.BirdPhotoModel;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/11/11
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("birdPhotoService")
public class BirdPhotoServiceImpl implements BirdPhotoService {
	@Autowired BirdPhotoDao dao;

	@Override
	public BirdPhotoModel findById(String id) {
		return dao.findById(id);
	}

	@Override
	public List<BirdPhotoModel> findAll() {
		return dao.findAll();
	}

	@Override
	public void save(BirdPhotoModel model) {
		dao.save(model);
	}

	@Override
	public void update(BirdPhotoModel model) {
		dao.update(model);
	}

	@Override
	public void delete(BirdPhotoModel model) {
		dao.delete(model);
	}

	@Override
	public void saveAll(List<BirdPhotoModel> birdNameModels) {
		for (BirdPhotoModel model: birdNameModels) {
			save(model);
		}
	}

	@Override
	public void shutdown() {
		dao.shutdown();
	}

    @Override
    public Collection<? extends BirdPhotoModel> findAllIneligible() {
        return dao.findAllIneligible();  //To change body of implemented methods use File | Settings | File Templates.
    }
}
