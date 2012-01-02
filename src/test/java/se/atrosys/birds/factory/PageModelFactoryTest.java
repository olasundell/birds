package se.atrosys.birds.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.MediaModel;
import se.atrosys.birds.model.PageModel;

import static org.testng.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/13/11
 * Time: 8:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class PageModelFactoryTest extends BaseTest {
	@Autowired PageModelFactory pageModelFactory;
	private final BirdModelBuilder birdModelBuilder = new BirdModelBuilder();
	private BirdModel birdModel;
	
	@BeforeMethod
	public void beforeMethod() {
		birdModel = birdModelBuilder.build();
	}

	@Test
	public void createPageModelShouldReturnValidModel() throws Exception {
		PageModel model = pageModelFactory.createPageModel(birdModel);
		// TODO add asserts
	}
	
	@Test
	public void shouldGetRandomMediaModel() {
		MediaModel mediaModel = pageModelFactory.getRandomMedia(birdModel);
		
		// TODO add asserts
		assertNotNull(mediaModel, "Expected a MediaModel instance");
	}
}
