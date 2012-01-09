package se.atrosys.birds.factory;

import org.apache.http.HttpRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.model.MediaType;
import se.atrosys.birds.model.SoundModel;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * TODO write comment
 */
public class SoundModelFactoryTest extends BaseTest {
	@Autowired SoundModelFactory factory;
	private final static String HREF = "http://foo.it/bar";
	
	@Test
	public void createModelShouldReturnInstance() throws Exception {
		SoundModel model = factory.createModel(HREF);
		
		assertNotNull(model, "model is null");
		assertTrue(model.isEligible(), "model isn't eligible as expected");
		assertEquals(model.getType(), MediaType.SOUND, "model doesn't have the correct media type");
	}
}
