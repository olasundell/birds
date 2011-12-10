package se.atrosys.birds;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.exception.CouldNotFindDetailsException;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.NoFamilyException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.factory.BirdModelListFactory;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.service.BirdService;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/9/11
 * Time: 8:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class DbSystemTest extends AbstractTest {
	@Autowired BirdModelListFactory birdModelListFactory;
	@Autowired BirdService birdService;
	
	@Test
	public void dbShouldWork() throws CouldNotFindNamesElementException, IOException, NoFamilyException, NoSuchLanguageException, CouldNotFindDetailsException {
		List<BirdModel> birdModels = birdModelListFactory.scrapeFromAviBase("/home/ola/code/birds/avibase.html");
		birdService.save(birdModels.get(0));
		List<BirdModel> list = birdService.findAll();	
		
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertNotNull(list.get(0).getFamily());
		assertNotNull(list.get(0).getNames(), "Names list is null");
		assertFalse(list.get(0).getNames().isEmpty(), "Names list is empty");
	}
}
