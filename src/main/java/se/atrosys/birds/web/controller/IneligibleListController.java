package se.atrosys.birds.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
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
	@Autowired MediaService mediaService;
	@Autowired SoundService soundService;

	@RequestMapping("/listineligible/")
	public ModelAndView list() {
        IneligibleModel ineligibleModel = new IneligibleModel();

//        soundService.findIneligible();

        return new ModelAndView("listineligible", "ieModel", ineligibleModel);
	}
}
