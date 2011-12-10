package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.atrosys.birds.db.BirdNameDao;
import se.atrosys.birds.model.BirdNameModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/10/11
 * Time: 9:56 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("birdNameService")
public class BirdNameServiceImpl implements BirdNameService {
	@Autowired BirdNameDao dao;

	@Override
	public BirdNameModel findById(String id) {
		return dao.findById(id);
	}

	@Override
	public List<BirdNameModel> findAll() {
		return dao.findAll();
	}

	@Override
	public void save(BirdNameModel model) {
		dao.save(model);
	}

	@Override
	public void update(BirdNameModel model) {
		dao.update(model);
	}

	@Override
	public void delete(BirdNameModel model) {
		dao.delete(model);
	}

	@Override
	public void saveAll(List<BirdNameModel> birdNameModels) {
		for (BirdNameModel model: birdNameModels) {
			save(model);
		}
	}

	@Override
	public void shutdown() {
		dao.shutdown();
	}
}
