package se.atrosys.birds.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import se.atrosys.birds.exception.CouldNotFindDetailsException;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.NoFamilyException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.factory.BirdModelListFactory;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.service.BirdService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/11/11
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class DbMain {
	@Autowired BirdModelListFactory birdModelListFactory;
	@Autowired BirdService birdService;

	public void importIntoDb() throws CouldNotFindNamesElementException, JAXBException, IOException, NoFamilyException, NoSuchLanguageException, CouldNotFindDetailsException {
		List<BirdModel> birdModels = birdModelListFactory.scrapeFromAviBase("/home/ola/code/birds/avibase.html");
		birdService.saveAll(birdModels);
	}

	public static void main(String[] args) throws CouldNotFindNamesElementException, JAXBException, IOException, NoSuchLanguageException, NoFamilyException, CouldNotFindDetailsException {
		final ApplicationContext ac =  new ClassPathXmlApplicationContext(new String[] {"classpath:/META-INF/spring/context.xml"});
		final DbMain launcher = ac.getBean("dbMain", DbMain.class);
		launcher.importIntoDb();
	}
}
