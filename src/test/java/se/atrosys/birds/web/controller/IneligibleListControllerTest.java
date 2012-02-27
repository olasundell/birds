package se.atrosys.birds.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.factory.BirdModelCollectionBuilder;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.BirdPhotoModel;
import se.atrosys.birds.model.SoundModel;
import se.atrosys.birds.service.BirdPhotoService;
import se.atrosys.birds.service.MediaService;
import se.atrosys.birds.service.SoundService;
import se.atrosys.birds.service.SoundServiceImpl;
import se.atrosys.birds.web.model.IneligibleModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.testng.Assert.*;

/**
 * TODO write comment
 */
public class IneligibleListControllerTest extends BaseTest {
	@Autowired IneligibleListController controller;

    @Qualifier("stubMediaService")
    @Autowired MediaService mediaService;
	@Autowired BirdPhotoService birdPhotoService;
	@Autowired SoundService soundService;

	BirdPhotoService stubBirdPhotoService = new StubBirdPhotoService();
	SoundService stubSoundService = new StubSoundService();

	@BeforeMethod
	public void switchServices() {
		controller.setBirdPhotoService(stubBirdPhotoService);
		controller.setSoundService(stubSoundService);
	}

	@AfterMethod
	public void resetServices() {
		controller.setBirdPhotoService(birdPhotoService);
		controller.setSoundService(soundService);
	}

/*    @BeforeClass
    public void setEligibility() throws CouldNotFindMediaException {
    }

    @AfterClass
    public void resetIneligible() {
        model.setEligible(true);
        mediaService.update(model);
    }*/

	@Test(groups = "system")
	public void controllerShouldSetView() {
		ModelAndView modelAndView = controller.list();

		assertNotNull(modelAndView, "Expected an instance.");
        assertEquals(modelAndView.getViewName(), "listineligible", "Wrong view name");
    }
    
    @Test
    public void controllerShouldSetModel() {
        ModelAndView modelAndView = controller.list();

        assertNotNull(modelAndView, "Expected an instance.");
        assertNotNull(modelAndView.getModel().get("ieModel"), "Model instance is null");
	}
    
    @Test
    public void modelShouldContainPhotos() {
        ModelAndView modelAndView = controller.list();

        assertNotNull(modelAndView, "Expected an instance.");
        IneligibleModel model = (IneligibleModel) modelAndView.getModel().get("ieModel");
        assertNotNull(model, "Model instance is null");

        List<BirdPhotoModel> list = model.getPhotos();
        assertNotNull(list, "Bird photo model list is null");
        assertFalse(list.isEmpty(), "Bird photo model list is empty");
    }

	public void setBirdPhotoService(BirdPhotoService birdPhotoService) {
		this.birdPhotoService = birdPhotoService;
	}

	public void setSoundService(SoundService soundService) {
		this.soundService = soundService;
	}

	private static class StubBirdPhotoService implements BirdPhotoService {
		@Override
		public BirdPhotoModel findById(String id) {
			return null;  //To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public List<BirdPhotoModel> findAll() {
			return null;  //To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public void save(BirdPhotoModel model) {
			//To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public void update(BirdPhotoModel model) {
			//To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public void delete(BirdPhotoModel model) {
			//To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public void saveAll(List<BirdPhotoModel> birdModels) {
			//To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public void shutdown() {
			//To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public Collection<? extends BirdPhotoModel> findAllIneligible() {
			List<BirdPhotoModel> list = new ArrayList<BirdPhotoModel>();

			for (BirdModel model: new BirdModelCollectionBuilder().build()) {
				list.addAll(model.getPhotos());
			}

			return list;
		}
	}

	private static class StubSoundService implements SoundService {
		@Override
		public void enrichAll(List<BirdModel> list) {
			//To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public SoundModel findById(String id) {
			return null;  //To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public Collection<? extends SoundModel> findAllIneligible() {
			List<SoundModel> list = new ArrayList<SoundModel>();

			for (BirdModel model: new BirdModelCollectionBuilder().build()) {
				list.addAll(model.getSounds());
			}

			return list;
		}
	}
}
