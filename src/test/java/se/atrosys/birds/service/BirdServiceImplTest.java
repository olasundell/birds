package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.db.BirdDao;
import se.atrosys.birds.db.BirdDaoImpl;
import se.atrosys.birds.factory.BirdModelBuilder;
import se.atrosys.birds.flickr.PhotoBuilder;
import se.atrosys.birds.model.*;

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
public class BirdServiceImplTest extends BaseTest {
	@Autowired BirdServiceImpl service;
	@Autowired FamilyService familyService;
	
	public static final String ID = "123ABC";
	public static final String HREF = String.format("foo avibaseid=%s", ID);
	public static final String SCIENTIFIC_NAME = "bar";
	private final BirdModel model = new BirdModelBuilder().build();
	private static final String FAMILY_NAME = "Faaaamily";
	private static final String GROUP_NAME = "Groupers";
	private static final List<BirdPhotoModel> photos = new ArrayList<BirdPhotoModel>();
	private final PhotoBuilder<BirdPhotoModel> photoBuilder = new PhotoBuilder<BirdPhotoModel>();
	private BirdDao oldDao;

	@BeforeClass
	public void beforeClass() {
		
		// switch dao
		oldDao = service.getDao();
		
		service.setDao(new BirdDao() {
			public BirdModel findById(String id) {
				return model;  //To change body of implemented methods use File | Settings | File Templates.
			}

			public List<BirdModel> findAll() {
				List<BirdModel> list = new ArrayList<BirdModel>();
				list.add(model);
				return list;  //To change body of implemented methods use File | Settings | File Templates.
			}

			public void save(BirdModel model) {
				//To change body of implemented methods use File | Settings | File Templates.
			}

			public void update(BirdModel model) {
				//To change body of implemented methods use File | Settings | File Templates.
			}

			public void delete(BirdModel model) {
				//To change body of implemented methods use File | Settings | File Templates.
			}

			public void shutdown() {
				//To change body of implemented methods use File | Settings | File Templates.
			}

			public BirdModel getRandomBird() {
				return model;
			}

			public BirdModel findByScientificName(String name) {
				return model;  //To change body of implemented methods use File | Settings | File Templates.
			}
		});
	}

	@AfterClass
	public void afterClass() {
		service.setDao(oldDao);
	}
	
	// TODO disabled as it changes data. Rewrite using mocking.
	@Test(enabled = false)
	public void saveShouldWork() throws InstantiationException, IllegalAccessException {
		model.setHref(HREF);
		model.setScientificName(SCIENTIFIC_NAME);
		photos.add(photoBuilder.build(BirdPhotoModel.class));
		model.setPhotos(photos);

		FamilyModel family = new FamilyModel();
		family.setFamily(FAMILY_NAME);
		GroupModel group = new GroupModel();
		group.setGroupName(GROUP_NAME);
		family.setGroup(group);
		familyService.save(family);

		model.setFamily(family);

		if (service.findById(model.getId()) != null) {
			service.delete(model);
		}

		service.save(model);
	}

//	@Test(dependsOnMethods = "saveShouldWork" )
	@Test(enabled = false)
	public void findBasedOnIdShouldWork() {
		BirdModel retrievedModel = service.findById(ID);
		assertEquals(retrievedModel.getHref(), HREF, "href wasn't what we expected");
		assertEquals(retrievedModel.getId(), ID, "id wasn't what we expected");
		assertEquals(retrievedModel.getScientificName(), SCIENTIFIC_NAME, "scientific name wasn't what we expected");
		assertEquals(retrievedModel.getFamily().getFamily(), FAMILY_NAME, "family name wasn't what we expected");
		assertEquals(retrievedModel.getFamily().getGroup().getGroupName(), GROUP_NAME, "group name wasn't what we expected");
		assertFalse(retrievedModel.getPhotos().isEmpty(), "Photo list is empty");
		assertEquals(retrievedModel.getPhotos().get(0), photos.get(0));
	}
	
//	@Test(dependsOnMethods = "saveShouldWork" )
	@Test(enabled = false)
	public void findAllShouldWork() {
		List<BirdModel> modelList = service.findAll();

		assertNotNull(modelList, "Model list is null");
		assertTrue(modelList.size() > 0, "Model list size is zero");
		assertEquals(modelList.get(0).getHref(), HREF, "href wasn't what we expected");
		assertEquals(modelList.get(0).getId(), ID, "id wasn't what we expected");
		assertEquals(modelList.get(0).getScientificName(), SCIENTIFIC_NAME, "scientific name wasn't what we expected");
		assertEquals(modelList.get(0).getFamily().getFamily(), FAMILY_NAME, "family name wasn't what we expected");
		assertEquals(modelList.get(0).getFamily().getGroup().getGroupName(), GROUP_NAME, "group name wasn't what we expected");
	}

}
