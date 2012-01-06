package se.atrosys.birds;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import se.atrosys.birds.exception.CouldNotFindDetailsException;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.NoFamilyException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.factory.BirdModelListFactory;
import se.atrosys.birds.factory.ScrapedTableBuilder;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.service.BirdService;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = "classpath:META-INF/spring/context.xml")
public class BaseTest extends AbstractTestNGSpringContextTests {
	protected Element table;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired(required = true) BirdModelListFactory birdModelListFactory;
	@Autowired(required = true) BirdService birdService;

	// this test is a prerequisite for all other tests requiring a database.
	@BeforeGroups(groups = "system")
	public void initDb() throws CouldNotFindNamesElementException, IOException, NoFamilyException, NoSuchLanguageException, CouldNotFindDetailsException, JAXBException {
		List<BirdModel> birdModels = birdModelListFactory.scrapeFromAviBase("/home/ola/code/birds/avibase-short.html");
		birdService.saveAll(birdModels);
		List<BirdModel> list = birdService.findAll();

		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertNotNull(list.get(0).getFamily());
		assertNotNull(list.get(0).getNames(), "Names list is null");
		assertFalse(list.get(0).getNames().isEmpty(), "Names list is empty");
	}

	public BaseTest() {
		ScrapedTableBuilder tableBuilder = new ScrapedTableBuilder();
		try {
			table = tableBuilder.getTable(Jsoup.parse(new File("avibase.html"), "UTF-8"));
		} catch (IOException e) {
			logger.error("Could not read file", e);
		}
	}
	
/*	@BeforeSuite(alwaysRun = true, enabled = true)
	public void beforeSuite() throws IOException {
		logger.info("beforeSuite");
		ScrapedTableBuilder tableBuilder = new ScrapedTableBuilder();
		table = tableBuilder.getTable(Jsoup.parse(new File("avibase.html"), "UTF-8"));
	}*/
}
