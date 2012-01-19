package se.atrosys.birds.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import se.atrosys.birds.service.BirdPhotoService;
import se.atrosys.birds.service.BirdService;
import se.atrosys.birds.service.MediaService;
import se.atrosys.birds.service.SoundService;
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

	@RequestMapping("/listineligible/")
	public ModelAndView list() {
        IneligibleModel ineligibleModel = new IneligibleModel();

        ineligibleModel.getPhotos().addAll(birdPhotoService.findAllIneligible());
        ineligibleModel.getSounds().addAll(soundService.findAllIneligible());

        return new ModelAndView("listineligible", "ieModel", ineligibleModel);
	}
}
