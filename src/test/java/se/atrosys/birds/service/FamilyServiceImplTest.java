package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.FamilyModel;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

/**
 * TODO write comment
 */
public class FamilyServiceImplTest extends BaseTest {
	@Autowired FamilyService familyService;
	
	@Test
	public void findFamilyShouldSetBirdModels() {
		List<FamilyModel> list = familyService.findAll();
		assertNotNull(list, "family list is null");
		assertFalse(list.isEmpty(), "family list is empty");
		
		List<BirdModel> birdList = list.get(0).getBirds();
		assertNotNull(birdList, String.format("list of birds belong to family %s is null", list.get(0).getFamily()));
		assertFalse(birdList.isEmpty(), String.format("list of birds belong to family %s is empty", list.get(0).getFamily()));
		
		assertNotNull(birdList.get(0), "First bird model in list was, in fact, null");
		assertNotNull(birdList.get(0).getId(), "First bird model in list had null id");
		assertFalse(birdList.get(0).getId().isEmpty(), "First bird model in list had empty id");
	}
}
