package se.atrosys.birds.factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.model.FamilyModel;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test class for the family model factory.
 */
public class FamilyModelFactoryTest extends BaseTest {
	private static final String ORDER = "ANSERIFORMES";
	private static final String FAMILY = "Anatidae";
	@Autowired FamilyModelFactory familyModelFactory;
	
	String htmlSnippet = new StringBuilder()
			.append("<tr valign=bottom><td colspan=3><P>&nbsp;<br><b>")
			.append(ORDER)
			.append(": ")
			.append(FAMILY)
			.append("</b></P></td></tr>").toString();

	private Element family;

	@BeforeMethod
	protected void setUp() throws Exception {
		family = Jsoup.parse(htmlSnippet).body();
	}

	@Test
	public void familyModelFactoryShouldReturnValidModel() {
		FamilyModel model = familyModelFactory.createModel(family);
		assertNotNull(model);
		assertEquals(model.getFamily(), FAMILY, "Family isn't what we expected");
	}
}
