package se.atrosys.birds.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.PageModel;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/13/11
 * Time: 8:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class PageModelFactoryTest extends AbstractTest {
	@Autowired PageModelFactory pageModelFactory;
	private final BirdModelBuilder birdModelBuilder = new BirdModelBuilder();

	@Test
	public void createPageModelShouldReturnValidModel() throws Exception {
		BirdModel birdModel = birdModelBuilder.build();

		PageModel model = pageModelFactory.createPageModel(birdModel);
	}
}
