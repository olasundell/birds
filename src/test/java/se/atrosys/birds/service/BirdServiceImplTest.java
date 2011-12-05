package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.model.BirdModel;

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
	public static final String ID = "123ABC";
	public static final String HREF = String.format("foo avibaseid=%s", ID);
	public static final String SCIENTIFIC_NAME = "bar";
	private final BirdModel model = new BirdModel();

	@Test
	public void saveShouldWork() {
		model.setHref(HREF);
		model.setScientificName(SCIENTIFIC_NAME);
		service.save(model);
	}
	
	@Test(dependsOnMethods = "saveShouldWork" )
	public void findAllShouldWork() {
		List<BirdModel> modelList = service.findAll();

		assertNotNull(modelList, "Model list is null");
		assertTrue(modelList.size() > 0, "Model list size is zero");
		assertEquals(modelList.get(0).getHref(), HREF);
		assertEquals(modelList.get(0).getId(), ID);
		assertEquals(modelList.get(0).getScientificName(), SCIENTIFIC_NAME);
	}
	
}
