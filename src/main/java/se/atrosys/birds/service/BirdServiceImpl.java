package se.atrosys.birds.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.atrosys.birds.db.BirdDao;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.factory.BirdModelListFactory;
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
	@Autowired BirdModelListFactory birdModelListFactory;
	@Autowired FamilyService familyService;
	@Autowired BirdNameService birdNameService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public BirdModel findById(String id) {
		return dao.findById(id);
	}

	public List<BirdModel> findAll() {
		return dao.findAll();
	}

	public void save(BirdModel model) throws NoSuchLanguageException {
		if (familyService.findById(model.getFamily().getFamily()) == null) {
			familyService.save(model.getFamily());
		}

		dao.save(model);

//		birdNameService.saveAll(model.getNames());
	}

	public void update(BirdModel model) {
		dao.update(model);
	}

	public void delete(BirdModel model) {
		dao.delete(model);
	}

	public void saveAll(List<BirdModel> birdModels) throws NoSuchLanguageException {
		for (BirdModel model: birdModels) {
			if (findById(model.getId()) != null) {
				update(model);
			} else {
				save(model);
			}
		}
	}

	public void shutdown() {
		dao.shutdown();
	}

	public BirdModel getRandomBird() {
		return dao.getRandomBird();
	}

	public BirdModel findByScientificName(String name) {
		return dao.findByScientificName(name);
	}

    public void clearAll() {
        dao.clearAll();
    }

    protected void setDao(BirdDao dao) {
		this.dao = dao;
	}

	public BirdDao getDao() {
		return dao;
	}
}
