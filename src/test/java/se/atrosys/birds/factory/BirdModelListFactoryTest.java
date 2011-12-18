package se.atrosys.birds.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.exception.CouldNotFindDetailsException;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.NoFamilyException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.FamilyModel;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 11/29/11
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class BirdModelListFactoryTest extends AbstractTest {
	@Autowired private BirdModelListFactory birdModelListFactory;

	@Test
    public void scrapeFromAviBaseShouldReturnList() throws IOException, CouldNotFindNamesElementException, NoSuchLanguageException, NoFamilyException, CouldNotFindDetailsException, JAXBException {
        List<BirdModel> list = birdModelListFactory.scrapeFromAviBase("avibase.html");
        
        assertNotNull(list, "List is null");
		assertEquals(list.size(), 924, "List did not contain the expected number of models.");
		
		Map<String, FamilyModel> familyModels = new HashMap<String, FamilyModel>();
		
		for (BirdModel model: list) {
			assertNotNull(model.getRegionalScarcity(), "Regional scarcity list is null.");
			familyModels.put(model.getFamily().getFamily(), model.getFamily());
		}
		
		assertEquals(familyModels.size(), 92, "Bird model list didn't have the expected number of families");
    }
}
