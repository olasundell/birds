package se.atrosys.birds.factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.factory.BirdModelFactory;
import se.atrosys.birds.model.BirdModel;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/3/11
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class BirdModelFactoryTest extends AbstractTest {
    public static final String ENGLISH_NAME = "White-faced Whistling-Duck";
    public static final String GERMAN_NAME= "Witwenpfeifgans";
    public static final String HREF = "species.jsp?avibaseid=A534AFEA126DBD62";
    public static final String SCIENTIFIC_NAME = "Dendrocygna viduata";
    String htmlSnippet = new StringBuilder()
            .append("<tr>")
            .append("<td>")
            .append(ENGLISH_NAME)
            .append("</td>")
            .append("<td><a href=\"")
            .append(HREF)
            .append("\"><i>")
            .append(SCIENTIFIC_NAME)
            .append("</i></a></td>")
            .append("<td>Rare/Accidental </td>")
            .append("</tr>").toString();
    @Autowired private BirdModelFactory factory;
    private Element detail;
    private Element bird;

    @BeforeMethod
    protected void setUp() throws Exception {
        detail = Jsoup.parse(new File("dendrocygnaviduata.html"), "UTF-8").body();
        bird = Jsoup.parse(htmlSnippet).body();
    }
    
    @Test
    public void findNamesElementShouldReturnCorrectElement() throws IOException, CouldNotFindNamesElementException {
        Element element = factory.findNamesElement(detail);
        
        assertNotNull(element, "Could not find names element");
        assertEquals(element.childNodes().size(), 93, "Could not find correct names element");
    }
    
    @Test(expectedExceptions = CouldNotFindNamesElementException.class)
    public void findNamesElementShouldThrowExceptionWithIncorrectElement() throws IOException, CouldNotFindNamesElementException {
        // the bird element is the wrong one, as it should be.
        factory.findNamesElement(bird);
    }
    
    @Test
    public void enrichModelFromAvibaseShouldEnrichProperly() throws CouldNotFindNamesElementException, NoSuchLanguageException {
        Element element = factory.findNamesElement(detail);
        BirdModel model = new BirdModel();
        factory.enrichModelFromAvibase(model, element);
        assertEquals(model.getName("de"), GERMAN_NAME, "german name does not match");
    }
    

    @Test
    public void shouldGetCorrectInstanceFromAviBaseSnippet() throws IOException, NoSuchLanguageException {
        BirdModel model = factory.createInitialInstance(bird);
        assertNotNull(model, "model is null");
        assertEquals(model.getScientificName(), SCIENTIFIC_NAME, "scientific name does not match");
        assertEquals(model.getName("en"), ENGLISH_NAME, "english name does not match");
        assertEquals(model.getHref(), HREF, "href does not match");
    }
	
	@Test
	public void shouldGetCorrectInstanceFromCreateModel() throws CouldNotFindNamesElementException, NoSuchLanguageException {
		BirdModel model = factory.createModel(bird, detail);
		assertNotNull(model, "model is null");
		assertEquals(model.getScientificName(), SCIENTIFIC_NAME, "scientific name does not match");
		assertEquals(model.getName("en"), ENGLISH_NAME, "english name does not match");
		assertEquals(model.getHref(), HREF, "href does not match");
		assertEquals(model.getName("de"), GERMAN_NAME, "german name does not match");
	}
    
}