package se.atrosys.birds.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.atrosys.birds.db.BirdDao;
import se.atrosys.birds.exception.CouldNotFindDetailsException;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.NoFamilyException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.factory.BirdModelListFactory;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.BirdNameModel;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
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

	public void save(BirdModel model) {
		if (familyService.findById(model.getFamily().getFamily()) == null) {
			familyService.save(model.getFamily());
		}

		dao.save(model);

		birdNameService.saveAll(model.getNames());

	}

	public void update(BirdModel model) {
		dao.update(model);
	}

	public void delete(BirdModel model) {
		dao.delete(model);
	}

	public void saveAll(List<BirdModel> birdModels) {
		for (BirdModel model: birdModels) {
			save(model);
		}
	}

	public void shutdown() {
		dao.shutdown();
	}

//	@PostConstruct
	public void postConstruct() {
		logger.info("postConstruct called");
		// load models.
		
		List<BirdModel> birdModels = new ArrayList<BirdModel>();

		try {
			birdModels.addAll(birdModelListFactory.scrapeFromAviBase("/home/ola/code/birds/avibase.html"));
		} catch (IOException e) {
			logger.error("An error occurred", e);
		} catch (CouldNotFindNamesElementException e) {
			logger.error("An error occurred", e);
		} catch (NoSuchLanguageException e) {
			logger.error("An error occurred", e);
		} catch (NoFamilyException e) {
			logger.error("An error occurred", e);
		} catch (CouldNotFindDetailsException e) {
			logger.error("An error occurred", e);
		} catch (JAXBException e) {
			logger.error("An error occurred", e);
		}

		for (BirdModel model: birdModels) {
			if (findById(model.getId()) == null) {
				save(model);
			}
		}
	}
}
