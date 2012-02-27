package se.atrosys.birds.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import se.atrosys.birds.exception.CouldNotFindMediaException;
import se.atrosys.birds.service.BirdPhotoService;
import se.atrosys.birds.service.BirdService;
import se.atrosys.birds.service.MediaService;
import se.atrosys.birds.service.SoundService;
import se.atrosys.birds.web.model.IneligibleCommandModel;
import se.atrosys.birds.web.model.IneligibleModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * TODO write comment
 */
@Controller
public class IneligibleListController {
	@Autowired SoundService soundService;
    @Autowired BirdPhotoService birdPhotoService;
	@Autowired MediaService mediaService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/listineligible/", method = RequestMethod.GET)
	public ModelAndView list() {
		return createModelAndView();
	}

	@RequestMapping(value = "/listineligible/", method = RequestMethod.POST)
	public ModelAndView setEligible(String mediaId, String mediaType) {
		try {
			mediaService.setEligible(mediaId, mediaType);
		} catch (CouldNotFindMediaException e) {
			logger.error("Could not find media", e);
		}
		
		return createModelAndView();
	}

	private ModelAndView createModelAndView() {
		IneligibleModel ineligibleModel = new IneligibleModel();

		ineligibleModel.getPhotos().addAll(birdPhotoService.findAllIneligible());
		ineligibleModel.getSounds().addAll(soundService.findAllIneligible());

		ModelAndView modelAndView = new ModelAndView("listineligible", "ieModel", ineligibleModel);

		IneligibleCommandModel ineligibleCommandCommand = new IneligibleCommandModel();
		modelAndView.addObject("ineligible", ineligibleCommandCommand);

		return modelAndView;
	}

	protected void setSoundService(SoundService soundService) {
		this.soundService = soundService;
	}

	protected void setBirdPhotoService(BirdPhotoService birdPhotoService) {
		this.birdPhotoService = birdPhotoService;
	}
}
