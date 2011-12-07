package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.FamilyModel;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 9:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class BirdServiceImplTest extends AbstractTest {
	@Autowired BirdService service;
	@Autowired FamilyService familyService;
	
	public static final String ID = "123ABC";
	public static final String HREF = String.format("foo avibaseid=%s", ID);
	public static final String SCIENTIFIC_NAME = "bar";
	private final BirdModel model = new BirdModel();
	private static final String FAMILY_NAME = "Faaaamily";
	private static final String GROUP_NAME = "Groupers";

	@AfterClass
	public void afterClass() {
		for (BirdModel m: service.findAll()) {
			service.delete(m);
		}
		
		for (FamilyModel m: familyService.findAll()) {
			familyService.delete(m);
		}
	}

	@Test
	public void saveShouldWork() {
		model.setHref(HREF);
		model.setScientificName(SCIENTIFIC_NAME);

		FamilyModel family = new FamilyModel();
		family.setFamily(FAMILY_NAME);
		family.setGroup(GROUP_NAME);
		familyService.save(family);

		model.setFamily(family);

		if (service.findById(model.getId()) != null) {
			service.delete(model);
		}

		service.save(model);
	}
	
	@Test(dependsOnMethods = "saveShouldWork" )
	public void findAllShouldWork() {
		List<BirdModel> modelList = service.findAll();

		assertNotNull(modelList, "Model list is null");
		assertTrue(modelList.size() > 0, "Model list size is zero");
		assertEquals(modelList.get(0).getHref(), HREF, "href wasn't what we expected");
		assertEquals(modelList.get(0).getId(), ID, "id wasn't what we expected");
		assertEquals(modelList.get(0).getScientificName(), SCIENTIFIC_NAME, "scientific name wasn't what we expected");
	}
	
}
