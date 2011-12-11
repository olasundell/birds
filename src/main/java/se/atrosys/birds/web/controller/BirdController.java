package se.atrosys.birds.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import se.atrosys.birds.exception.CouldNotFindDetailsException;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.NoFamilyException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.factory.BirdModelListFactory;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.service.BirdService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/5/11
 * Time: 8:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class BirdController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired protected BirdService service;
	@Autowired protected BirdModelListFactory birdModelListFactory;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String randomBird(HttpServletResponse response, BirdModel birdModel) {
		logger.info("randomBird called");
		List<BirdModel> list = service.findAll();

		if (list.size() == 0) {
			try {
				List<BirdModel> birdModels = birdModelListFactory.scrapeFromAviBase("/home/ola/code/birds/avibase.html");
				service.saveAll(birdModels);
				list = service.findAll();	
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
		}

		if (list.size() == 0) {
			response.setStatus(500);
			return "error";
		}

		BirdModel randomModel = list.get(new Random().nextInt(list.size()));

		birdModel.setHref(randomModel.getHref());
		birdModel.setScientificName(randomModel.getScientificName());
		birdModel.setFamily(randomModel.getFamily());
		birdModel.setNames(randomModel.getNames());

		return "random";
	}
}
