package se.atrosys.birds.model;

import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;

import static org.testng.Assert.assertEquals;

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
}
