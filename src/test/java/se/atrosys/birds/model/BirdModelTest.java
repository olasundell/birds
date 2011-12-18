package se.atrosys.birds.model;

import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 8:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class BirdModelTest extends AbstractTest {
	private final static String HREF = "species.jsp?avibaseid=A534AFEA126DBD62";
	private final static String ID = "A534AFEA126DBD62";
	
	@Test
	public void setHrefShouldAlsoSetId() throws Exception {
		BirdModel model = new BirdModel();
		model.setHref(HREF);
		assertEquals(model.getId(), ID, String.format("ID %s does not match expected value %s", model.getId(), ID));
	}
	
	@Test
	public void birdNamesMapShouldBeCreated() {
		BirdModel model = new BirdModel();
		ArrayList<BirdNameModel> birdNameModels = new ArrayList<BirdNameModel>();
		BirdNameModel e = new BirdNameModel();
		
		String english = "English";
		e.setLang(english);
		String parakeet = "Parakeet";
		e.setName(parakeet);
		
		birdNameModels.add(e);
		model.setNames(birdNameModels);
		
		assertTrue(model.getNameMap().containsKey(english));
		assertTrue(model.getNameMap().containsValue(parakeet));
	}
}
