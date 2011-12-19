package se.atrosys.birds.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.model.RegionModel;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/17/11
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegionModelFactoryTest extends BaseTest {
	@Autowired RegionModelFactory regionModelFactory;
	private static final String NAME = "name";

	@Test
	public void propertiesShouldBeSet() {
		RegionModel regionModel = regionModelFactory.createModel(NAME);

		assertNotNull(regionModel, "model is null");
		assertEquals(regionModel.getName(), NAME, "Names are not equal");
	}
}
