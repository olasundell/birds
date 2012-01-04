package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.model.BirdPhotoModel;
import se.atrosys.birds.model.MediaModel;
import se.atrosys.birds.model.PhotoModel;
import se.atrosys.birds.model.SoundModel;

/**
 * TODO write comment
 */
public class MediaServiceImplTest extends BaseTest {
	private final String PHOTO_ID = "123";
	private final String SOUND_ID = "321";

	@Autowired MediaService mediaService;
	BirdPhotoModel photoMedia;
	SoundModel soundMedia;
	
	@BeforeTest
	public void beforeTest() {
		photoMedia = new BirdPhotoModel();
		photoMedia.setId(PHOTO_ID);
		soundMedia = new SoundModel();
	}
	
	@Test
	public void shouldSetPhotoMediaAsIneligible() {
		photoMedia.setEligible(true);
		mediaService.setIneligible(photoMedia.getId(), photoMedia.getType().toString());
	}
	
	@Test
	public void shouldSetSoundMediaAsIneligible() {
		soundMedia.setEligible(true);
		mediaService.setIneligible(soundMedia.getId(), soundMedia.getType().toString());
	}
}
