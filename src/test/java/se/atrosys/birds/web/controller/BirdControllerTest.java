package se.atrosys.birds.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.factory.BirdModelBuilder;
import se.atrosys.birds.model.*;
import se.atrosys.birds.service.BirdService;
import se.atrosys.birds.service.BirdServiceImpl;
import se.atrosys.birds.service.MediaService;
import se.atrosys.birds.service.MediaServiceImpl;
import se.atrosys.birds.web.model.CommandModel;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/7/11
 * Time: 8:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class BirdControllerTest extends BaseTest {
	public static final String HREF = "bar avibaseid=321";
	public static final String SCIENTIFIC_NAME = "Scifinameisch";
	@Autowired BirdController controller;
	private BirdModel birdModel;
	private BirdModel otherBirdModel;
	private BirdServiceImpl oldBirdService;
	private static final String FAULTY_ID = "FAAAAULTY";
	private MediaServiceImpl oldMediaService;

	@BeforeMethod
	protected void setUp() throws Exception {
		birdModel = new BirdModelBuilder().setHref(HREF).setScientificName(SCIENTIFIC_NAME).build();
		otherBirdModel = new BirdModelBuilder().setHref("ladlas avibaseid=1231231").setScientificName("foobar").build();
		
		oldBirdService = (BirdServiceImpl) ReflectionTestUtils.getField(controller, "birdService");
		oldMediaService = (MediaServiceImpl) ReflectionTestUtils.getField(controller, "mediaService");
		
		List<MediaModel> mediaModels = new ArrayList<MediaModel>();
		mediaModels.addAll(birdModel.getPhotos());
		mediaModels.addAll(birdModel.getSounds());
		mediaModels.addAll(otherBirdModel.getPhotos());
		mediaModels.addAll(otherBirdModel.getSounds());

		ReflectionTestUtils.setField(controller, "birdService", new TestBirdServiceImpl());
		ReflectionTestUtils.setField(controller, "mediaService", new TestMediaServiceImpl(mediaModels));
		
	}

	@AfterMethod
	public void afterMethod() {
		ReflectionTestUtils.setField(controller, "birdService", oldBirdService);
		ReflectionTestUtils.setField(controller, "mediaService", oldMediaService);
	}

	@Test
	public void controllerShouldReturnRandomBirdWhenIdParameterIsNull() {
		ModelAndView modelAndView = buildModelAndView(birdModel);

		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("birdid")).thenReturn("");
		ModelAndView actual = controller.randomBird(request);
		Assert.assertEquals(actual.getViewName(), modelAndView.getViewName());
		// TODO re-add this later, we need a proper builder for the PageModel and it's just lots of sweat and little gain atm
/*		PageModel pageModel1 = (PageModel) actual.getModel().get("pageModel");
		Assert.assertEquals(pageModel1, pageModel);*/
//		assertTrue(pageModel1.equals(pageModel));
	}

	private ModelAndView buildModelAndView(BirdModel birdModelInPageModel) {
		final PageModel pageModel = new PageModel();
		pageModel.setBirdModel(birdModelInPageModel);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("random");
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.addObject("command", new CommandModel());
		
		return modelAndView;
	}

	@Test
	public void controllerShouldReturnRandomBirdWhenIdParameterIsSet() {
		ModelAndView modelAndView = buildModelAndView(otherBirdModel);

		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("birdid")).thenReturn(otherBirdModel.getId());
		ModelAndView actual = controller.randomBird(request);
		
		Assert.assertEquals(actual.getViewName(), modelAndView.getViewName());
	}

	@Test
	public void controllerShouldReturnErrorMessageWhenIdParameterIsFaulty() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("birdid")).thenReturn(FAULTY_ID);
		ModelAndView actual = controller.randomBird(request);
		
		assertNotNull(actual);
		assertEquals(actual.getViewName(), "error", "view name ");
	}
	
	@Test
	public void controllerShouldReturnCorrectAnswerWhenCorrectIdIsPosted() {
		String answer = controller.checkAnswer(birdModel.getScientificName(), birdModel.getId());
		
		assertNotNull(answer, "Null answer from controller");
		assertEquals(answer, birdModel.getScientificNameUnderscore(), "Controller answer isn't as expected");
	}

	@Test
	public void controllerShouldReturnCorrectAnswerWhenIncorrectIdIsPosted() {
		String answer = controller.checkAnswer(otherBirdModel.getScientificName(), birdModel.getId());

		assertNotNull(answer, "Null answer from controller");
		assertEquals(answer, birdModel.getScientificNameUnderscore(), "Controller answer isn't as expected");
	}

	@Test
	public void eligibleCommandModelShouldBeSet() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("birdid")).thenReturn("");
		ModelAndView actual = controller.randomBird(request);
		
		assertNotNull(actual);
		Object eligible = actual.getModel().get("ineligible");
		assertNotNull(eligible);
	}

	@Test
	public void shouldSetIneligibleWhenAskedTo() {
		BirdPhotoModel photoModel = birdModel.getPhotos().get(0);
		assertTrue(photoModel.isEligible());

		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("birdid")).thenReturn("");

		controller.ineligible(request, photoModel.getId(), photoModel.getType().toString());

		assertFalse(photoModel.isEligible());
	}
	
	@Test
	public void controllerShouldUseAllMediaTypesByDefault() {
		// TODO write me
	}
	
	@Test
	public void controllerShouldOnlyUsePhotoMediaWhenCorrespondingOptionIsSet() {
		// TODO write me
	}
	
	@Test
	public void controllerShouldOnlyUseSoundMediaWhenCorrespondingOptionIsSet() {
		// TODO write me
	}

	protected class TestBirdServiceImpl implements BirdService {
		@Override
		public BirdModel findByScientificName(String name) {
			if (birdModel.getScientificName().equals(name)) {
				return birdModel;
			} else if (otherBirdModel.getScientificName().equals(name)) {
				return otherBirdModel;
			}

			return null;
		}

        @Override
        public void clearAll() {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
		public BirdModel findById(String id) {
			if (birdModel.getId().equals(id)) {
				return birdModel;

			} else if (otherBirdModel.getId().equals(id)) {
				return otherBirdModel;
			}

			return null;
		}

		@Override
		public List<BirdModel> findAll() {
			return null;  //To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public void save(BirdModel model) throws NoSuchLanguageException {
			//To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public void update(BirdModel model) {
			//To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public void delete(BirdModel model) {
			//To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public void saveAll(List<BirdModel> birdModels) throws NoSuchLanguageException {
			//To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public void shutdown() {
			//To change body of implemented methods use File | Settings | File Templates.
		}

		public BirdModel getRandomBird() {
			return birdModel;
		}
	}

	private class TestMediaServiceImpl implements MediaService {
		Map<String, MediaModel> modelMap;
		int mediaId = 0;
		
		public TestMediaServiceImpl(List<MediaModel> mediaModels) {
			modelMap = new HashMap<String, MediaModel>();
			for (MediaModel model: mediaModels) {
				if (model.getId() == null || model.getId().isEmpty() || modelMap.containsKey(model.getId())) {
					switch (model.getType()) {
					case PHOTO :
						((BirdPhotoModel)model).setId(String.valueOf(mediaId++));
						break;
					case SOUND :
						((SoundModel)model).setId(mediaId++);
						break;
					}
				}
				
				modelMap.put(model.getId(), model);
			}
		}

		@Override
		public void setIneligible(String mediaId, String mediaType) {
			modelMap.get(mediaId).setEligible(false);
		}
	}
}
