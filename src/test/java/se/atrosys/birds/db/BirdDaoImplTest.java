package se.atrosys.birds.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.MediaModel;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * TODO write comment
 */
@Test(groups = "system")
public class BirdDaoImplTest extends BaseTest {
	private static final String SCIENTIFIC_NAME = "Anser fabalis";
	@Autowired BirdDao dao;

	// TODO this test fails because Derby SQL != Postgres SQL
	@Test(enabled = false)
	public void getRandomBirdShouldReturnBirdModel() {
		BirdModel model = dao.getRandomBird();
		
		assertNotNull(model, "returned randomised bird was null");
		assertNotNull(model.getId(), "returned randomised bird had null ID");
		assertFalse(model.getId().isEmpty(), "returned randomised bird had empty ID");
	}
	
	@Test
	public void getBirdByScientificNameShouldReturnBirdModel() {
		BirdModel model = dao.findByScientificName(SCIENTIFIC_NAME);

		assertNotNull(model, String.format("Tried to get bird by scifiname %s, bird was null", SCIENTIFIC_NAME));
		assertNotNull(model.getId(), "returned bird had null ID");
		assertFalse(model.getId().isEmpty(), "returned bird had empty ID");
	}


	// TODO fix this stuff.
	@Test(enabled = false)
	public void shouldOnlyHaveEligibleMediaModels() {
		List<BirdModel> list = dao.findAll();

		for (BirdModel model: list) {
			for (MediaModel soundModel: model.getSounds()) {
				assertTrue(soundModel.isEligible());
			}

			for (MediaModel photoModel: model.getPhotos()) {
				assertTrue(photoModel.isEligible());
			}
		}
	}
}
