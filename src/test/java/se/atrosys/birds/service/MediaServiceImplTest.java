package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.exception.CouldNotFindMediaException;
import se.atrosys.birds.factory.BirdModelCollectionBuilder;
import se.atrosys.birds.model.*;

import java.util.List;

import static org.testng.Assert.assertFalse;

/**
 * TODO write comment
 */
public class MediaServiceImplTest extends BaseTest {
	@Autowired BirdService birdService;
	@Autowired MediaService mediaService;
	@Autowired BirdPhotoService birdPhotoService;
	@Autowired SoundService soundService;
    @Autowired BirdModelCollectionBuilder birdModelCollectionBuilder;
	
	BirdPhotoModel photoMedia;
	SoundModel soundMedia;
	
	@BeforeMethod
	public void beforeTest() {
		photoMedia = findPhotoModel();
		soundMedia = findSoundModel();
	}

	private SoundModel findSoundModel() {
		List<BirdModel> list = birdService.findAll();

		for (BirdModel birdModel: list) {
			for (SoundModel soundModel: birdModel.getSounds()) {
				if (soundModel.isEligible()) {
					return soundModel;
				}
			}
		}

		return null;
	}

	private BirdPhotoModel findPhotoModel() {
		List<BirdModel> list = birdService.findAll();

		for (BirdModel birdModel: list) {
			for (BirdPhotoModel birdPhotoModel: birdModel.getPhotos()) {
				if (birdPhotoModel.isEligible()) {
					return birdPhotoModel;
				}
			}
		}
		
		return null;
	}

	@Test
	public void shouldSetPhotoMediaAsIneligible() throws CouldNotFindMediaException {
		photoMedia.setEligible(true);
		mediaService.setIneligible(photoMedia.getId(), photoMedia.getType().toString());

		photoMedia = birdPhotoService.findById(photoMedia.getId());

		assertFalse(photoMedia.isEligible());
	}
	
	@Test
	public void shouldSetSoundMediaAsIneligible() throws CouldNotFindMediaException {
		soundMedia.setEligible(true);
		mediaService.setIneligible(soundMedia.getId(), soundMedia.getType().toString());
		
		soundMedia = soundService.findById(soundMedia.getId());
		
		assertFalse(soundMedia.isEligible());
	}
}
