package se.atrosys.birds.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.model.BirdModel;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

/**
 * TODO write comment
 */
public class BirdDaoImplTest extends AbstractTest {
	@Autowired BirdDao dao;
	
	@Test
	public void getRandomBirdShouldReturnBirdModel() {
		BirdModel model = dao.getRandomBird();
		
		assertNotNull(model, "returned randomised bird was null");
		assertNotNull(model.getId(), "returned randomised bird had null ID");
		assertFalse(model.getId().isEmpty(), "returned randomised bird had empty ID");
	}
	
	@Test
	public void getBirdByScientificNameShouldReturnBirdModel() {
		BirdModel model = dao.findByScientificName("Anser fabalis");

		assertNotNull(model, "returned randomised bird was null");
		assertNotNull(model.getId(), "returned randomised bird had null ID");
		assertFalse(model.getId().isEmpty(), "returned randomised bird had empty ID");
	}
}
