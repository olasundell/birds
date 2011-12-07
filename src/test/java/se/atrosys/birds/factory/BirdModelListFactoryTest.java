package se.atrosys.birds.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.factory.BirdModelListFactory;
import se.atrosys.birds.model.BirdModel;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

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
    public void scrapeFromAviBaseShouldReturnList() throws IOException, CouldNotFindNamesElementException, NoSuchLanguageException {
        List<BirdModel> list = birdModelListFactory.scrapeFromAviBase("avibase.html");
        
        assertNotNull(list, "List is null");
        assertTrue(list.size() > 0, "List is empty");
    }
}
