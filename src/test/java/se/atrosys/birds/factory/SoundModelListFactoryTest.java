package se.atrosys.birds.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.exception.CouldNotFindSoundsException;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.SoundModel;
import se.atrosys.birds.util.FileFetcher;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/18/11
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class SoundModelListFactoryTest extends BaseTest {
	@Autowired SoundModelListFactory soundModelListFactory;
	
	@Test
	public void shouldGetProperListFromFactory() throws CouldNotFindSoundsException {
		BirdModel birdModel = new BirdModelBuilder().build();
		List<SoundModel> soundModelList = soundModelListFactory.createList(birdModel);
		assertNotNull(soundModelList, "List is null");
		assertFalse(soundModelList.isEmpty(), "List is empty");
	}

	// TODO this fscking test doesn't fail because xeno-canto returns 200 for all URLs under /species/*
	@Test(enabled=false, expectedExceptions = CouldNotFindSoundsException.class)
	public void shouldGetExceptionFromNonExistingScientificName() throws CouldNotFindSoundsException {
		BirdModel birdModel = new BirdModelBuilder().setScientificName("Blablahii blabbus").build();
		soundModelListFactory.createList(birdModel);
	}
	
	@Test
	public void shouldDownloadSound() throws CouldNotFindSoundsException {
		BirdModel birdModel = new BirdModelBuilder().setScientificName("Blablahii blabbus").build();
		
		soundModelListFactory.setFileFetcher(new FileFetcher() {
			public File fetchFile(Map<String, String> getParams, String fileUrl, String httpUrl) {
				return new File("sounds/Anser-anser");
			}
		});

		List<SoundModel> soundModelList = soundModelListFactory.createList(birdModel);
		assertNotNull(soundModelList, "List is null");
		assertFalse(soundModelList.isEmpty(), "List is empty");
	}
}
