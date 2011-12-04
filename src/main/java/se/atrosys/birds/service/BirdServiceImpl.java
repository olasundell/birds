package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.atrosys.birds.db.BirdDao;
import se.atrosys.birds.model.BirdModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("birdService")
public class BirdServiceImpl implements BirdService {
	@Autowired BirdDao dao;
	@Override
	public BirdModel findById(String id) {
		return dao.findById(id);
	}

	@Override
	public List<BirdModel> findAll() {
		return dao.findAll();
	}

	@Override
	public void save(BirdModel model) {
		dao.save(model);
	}

	@Override
	public void update(BirdModel model) {
		dao.update(model);
	}

	@Override
	public void delete(BirdModel model) {
		dao.delete(model);
	}

	@Override
	public void shutdown() {
		dao.shutdown();
	}
}
