package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.PageModel;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/11/11
 * Time: 8:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class BirdRandomiserServiceTest extends AbstractTest {
	@Autowired BirdRandomiserService service;
	
	@Test
	public void randomiseBirdShouldReturnValidModel() throws Exception {
		PageModel model = service.randomiseBird();
		assertNotNull(model.getBirdModel(), "service did not return a random bird model");

		if (!model.getBirdModel().getFamily().getBirds().isEmpty()) {
			assertNotEquals(0, model.getSiblings().size(), "Siblings list is empty");
		}
	}
}
