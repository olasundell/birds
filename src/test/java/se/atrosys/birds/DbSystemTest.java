package se.atrosys.birds;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.NoFamilyException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.factory.BirdModelListFactory;
import se.atrosys.birds.model.BirdModel;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/9/11
 * Time: 8:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class DbSystemTest {
/*	@Autowired BirdModelListFactory birdModelListFactory;
	@Test
	public void dbShouldWork() throws CouldNotFindNamesElementException, IOException, NoFamilyException, NoSuchLanguageException {
		List<BirdModel> birdModels = birdModelListFactory.scrapeFromAviBase("/home/ola/code/birds/avibase.html");
		service.saveAll(birdModels);
		list = service.findAll();	
	}*/
}
