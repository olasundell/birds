package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.flickr.PhotoBuilder;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.BirdPhotoModel;
import se.atrosys.birds.model.FamilyModel;
import se.atrosys.birds.model.PhotoModel;

import java.util.ArrayList;
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
	private static final List<BirdPhotoModel> photos = new ArrayList<BirdPhotoModel>();
	private final PhotoBuilder<BirdPhotoModel> photoBuilder = new PhotoBuilder<BirdPhotoModel>();

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
	public void saveShouldWork() throws InstantiationException, IllegalAccessException {
		model.setHref(HREF);
		model.setScientificName(SCIENTIFIC_NAME);
		photos.add(photoBuilder.build(BirdPhotoModel.class));
		model.setPhotos(photos);

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
	public void findBasedOnIdShouldWork() {
		BirdModel retrievedModel = service.findById(ID);
		assertEquals(retrievedModel.getHref(), HREF, "href wasn't what we expected");
		assertEquals(retrievedModel.getId(), ID, "id wasn't what we expected");
		assertEquals(retrievedModel.getScientificName(), SCIENTIFIC_NAME, "scientific name wasn't what we expected");
		assertEquals(retrievedModel.getFamily().getFamily(), FAMILY_NAME, "family name wasn't what we expected");
		assertEquals(retrievedModel.getFamily().getGroup(), GROUP_NAME, "group name wasn't what we expected");
		assertFalse(retrievedModel.getPhotos().isEmpty(), "Photo list is empty");
		assertEquals(retrievedModel.getPhotos().get(0), photos.get(0));
	}
	
	@Test(dependsOnMethods = "saveShouldWork" )
	public void findAllShouldWork() {
		List<BirdModel> modelList = service.findAll();

		assertNotNull(modelList, "Model list is null");
		assertTrue(modelList.size() > 0, "Model list size is zero");
		assertEquals(modelList.get(0).getHref(), HREF, "href wasn't what we expected");
		assertEquals(modelList.get(0).getId(), ID, "id wasn't what we expected");
		assertEquals(modelList.get(0).getScientificName(), SCIENTIFIC_NAME, "scientific name wasn't what we expected");
		assertEquals(modelList.get(0).getFamily().getFamily(), FAMILY_NAME, "family name wasn't what we expected");
		assertEquals(modelList.get(0).getFamily().getGroup(), GROUP_NAME, "group name wasn't what we expected");
	}
	
}
