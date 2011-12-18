package se.atrosys.birds.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import se.atrosys.birds.factory.PageModelFactory;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.PageModel;
import se.atrosys.birds.service.BirdService;

import javax.servlet.http.HttpServletRequest;

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
	@Autowired BirdService birdService;
	@Autowired PageModelFactory pageModelFactory;
	
	@RequestMapping(value = "/random/", method = RequestMethod.POST)
	public @ResponseBody String checkAnswer(@RequestParam String choice, @RequestParam String id) {
		if (birdService.findByScientificName(choice.replace('_', ' ')).getId().equals(id)) {
			return choice.replace(' ', '_');
		} else {
			return birdService.findById(id).getScientificName().replace(' ', '_');
		}
	}

	@RequestMapping(value = "/random/", method = RequestMethod.GET)
	public ModelAndView randomBird(HttpServletRequest request) {
		ModelAndView modelAndView = doControl(request);

		return modelAndView;
	}

	private ModelAndView doControl(HttpServletRequest request) {
		BirdModel model;

		String birdId = request.getParameter("birdid");

		logger.info(String.format("randomBird called with id %s", birdId));

		if (birdId != null && !birdId.isEmpty()) {
			model = birdService.findById(birdId);
		} else {
			model = birdService.getRandomBird();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("random");
		PageModel pageModel = pageModelFactory.createPageModel(model);
		pageModel.setPreviousAnswer((String) request.getAttribute("result"));
		modelAndView.addObject("pageModel", pageModel);
		CommandModel commandModel = new CommandModel();
		commandModel.setId(model.getId());
		modelAndView.addObject("answer", commandModel);
		return modelAndView;
	}

	public static class CommandModel {
		private String choice;
		private String id;

		public String getChoice() {
			return choice;
		}

		public void setChoice(String choice) {
			this.choice = choice;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}
}
