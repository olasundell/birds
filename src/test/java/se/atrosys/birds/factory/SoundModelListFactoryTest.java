package se.atrosys.birds.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.exception.CouldNotFindSoundsException;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.SoundModel;

import java.util.List;

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
	}
}
