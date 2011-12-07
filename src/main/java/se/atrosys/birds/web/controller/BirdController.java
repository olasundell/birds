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
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.service.BirdService;

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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String randomBird(BirdModel birdModel) {
		logger.info("randomBird called");
		List<BirdModel> list = service.findAll();

		if (list.size() > 0) {
			BirdModel randomModel = list.get(new Random(0).nextInt(list.size()));

			birdModel.setHref(randomModel.getHref());
			birdModel.setScientificName(randomModel.getScientificName());
		}

		return "random";
	}
}
