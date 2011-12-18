package se.atrosys.birds.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.factory.BirdModelBuilder;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.PageModel;
import se.atrosys.birds.service.BirdServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotNull;

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
	private BirdModel otherBirdModel;
	private BirdServiceImpl birdService;

	@BeforeMethod
	protected void setUp() throws Exception {
		birdModel = new BirdModelBuilder().setHref(HREF).setScientificName(SCIENTIFIC_NAME).build();
		otherBirdModel = new BirdModelBuilder().setHref("ladlas avibaseid=1231231").setScientificName("foobar").build();

		birdService = (BirdServiceImpl) ReflectionTestUtils.getField(controller, "birdService");

		ReflectionTestUtils.setField(controller, "birdService", new BirdServiceImpl() {
			public BirdModel findByScientificName(String name) {
				if (birdModel.getScientificName().equals(name)) {
					return birdModel;
				} else {
					return otherBirdModel;
				}
			}
			
			public BirdModel findById(String id) {
				if (birdModel.getId().equals(id)) {
					return birdModel;
				} else {
					return otherBirdModel;
				}
			}
			
			public BirdModel getRandomBird() {
				return birdModel;
			}
		});

	}

	@AfterMethod
	public void afterMethod() {
		ReflectionTestUtils.setField(controller, "birdService", birdService);
	}

	@Test
	public void controllerShouldReturnRandomBirdWhenParameterIsNull() {
		final PageModel pageModel = new PageModel();
		pageModel.setBirdModel(birdModel);


		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("random");
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.addObject("command", new BirdController.CommandModel());

		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("birdid")).thenReturn("");
		ModelAndView actual = controller.randomBird(request);
		Assert.assertEquals(actual.getViewName(), modelAndView.getViewName());
		// TODO re-add this later, we need a proper builder for the PageModel and it's just lots of sweat and little gain atm
/*		PageModel pageModel1 = (PageModel) actual.getModel().get("pageModel");
		Assert.assertEquals(pageModel1, pageModel);*/
//		assertTrue(pageModel1.equals(pageModel));
	}
	
	@Test
	public void controllerShouldReturnStatusAfterPost() {
//		Map<String, String> answer = controller.checkAnswer(birdModel.getId(), birdModel.getScientificName());
		String answer = controller.checkAnswer(birdModel.getId(), birdModel.getScientificName());
		
		assertNotNull(answer, "answer map is null");
	}

	// TODO writeme
/*	@Test
	public void controllerShouldReturnGivenBirdWhenParameterIsSet() {
		controller.randomBird("");
	}*/
}
