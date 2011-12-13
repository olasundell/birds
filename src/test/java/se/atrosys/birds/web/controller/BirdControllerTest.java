package se.atrosys.birds.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.factory.BirdModelBuilder;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.PageModel;
import se.atrosys.birds.service.BirdRandomiserService;
import se.atrosys.birds.service.BirdServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/7/11
 * Time: 8:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class BirdControllerTest extends AbstractTest {
	public static final String HREF = "bar avibaseid=321";
	public static final String SCIENTIFIC_NAME = "Scifinameisch";
	@Autowired BirdController controller;
	private BirdModel birdModel;

	@BeforeMethod
	protected void setUp() throws Exception {
		birdModel = new BirdModelBuilder().setHref(HREF).setScientificName(SCIENTIFIC_NAME).build();
		birdModel.setHref(HREF);
		birdModel.setScientificName(SCIENTIFIC_NAME);

	}

	@Test
	public void controllerShouldReturnRandomBirdWhenParameterIsNull() {
		final PageModel pageModel = new PageModel();
		pageModel.setBirdModel(birdModel);

		BirdServiceImpl birdService = (BirdServiceImpl) ReflectionTestUtils.getField(controller, "birdService");
		ReflectionTestUtils.setField(controller, "birdService", new BirdServiceImpl() {
			public BirdModel getRandomBird() {
				return birdModel;
			}
		});

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("random");
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.addObject("command", new BirdController.CommandModel());

		ModelAndView actual = controller.randomBird(null);
		Assert.assertEquals(actual.getViewName(), modelAndView.getViewName());
		// TODO re-add this later, we need a proper builder for the PageModel and it's just lots of sweat and little gain atm
/*		PageModel pageModel1 = (PageModel) actual.getModel().get("pageModel");
		Assert.assertEquals(pageModel1, pageModel);*/
//		assertTrue(pageModel1.equals(pageModel));
		ReflectionTestUtils.setField(controller, "birdService", birdService);
	}

	// TODO writeme
/*	@Test
	public void controllerShouldReturnGivenBirdWhenParameterIsSet() {
		controller.randomBird("");
	}*/
}
