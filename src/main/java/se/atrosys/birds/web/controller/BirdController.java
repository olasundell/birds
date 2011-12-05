package se.atrosys.birds.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import se.atrosys.birds.model.BirdModel;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/5/11
 * Time: 8:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class BirdController {
	@RequestMapping("/")
	public @ResponseBody String randomBird() {
		BirdModel birdModel = new BirdModel();

		birdModel.setHref("HREF");
		birdModel.setScientificName("Scifiname");

		return "bird";
	}
}
