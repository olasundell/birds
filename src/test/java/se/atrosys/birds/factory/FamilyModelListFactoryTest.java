package se.atrosys.birds.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.model.FamilyModel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/8/11
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class FamilyModelListFactoryTest extends AbstractTest {
	@Autowired FamilyModelListFactory factory;
	
	@Test
	public void scrapeFromAviBaseShouldReturnList() throws IOException {
		Map<String, FamilyModel> map = factory.scrapeFromAviBase("avibase.html");

		assertNotNull(map, "Map is null");
		assertEquals(map.size(), 92, "Map isn't the correct size");
	}
}
