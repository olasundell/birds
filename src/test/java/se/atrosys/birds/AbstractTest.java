package se.atrosys.birds;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import se.atrosys.birds.factory.ScrapedTableBuilder;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = "classpath:META-INF/spring/context.xml")
public class AbstractTest extends AbstractTestNGSpringContextTests {
	protected Element table;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public AbstractTest() {
		ScrapedTableBuilder tableBuilder = new ScrapedTableBuilder();
		try {
			table = tableBuilder.getTable(Jsoup.parse(new File("avibase.html"), "UTF-8"));
		} catch (IOException e) {
			logger.error("Could not read file", e);
		}
	}
	
	@BeforeSuite(alwaysRun = true, enabled = true)
	public void beforeSuite() throws IOException {
		logger.info("beforeSuite");
		ScrapedTableBuilder tableBuilder = new ScrapedTableBuilder();
		table = tableBuilder.getTable(Jsoup.parse(new File("avibase.html"), "UTF-8"));
	}
}
