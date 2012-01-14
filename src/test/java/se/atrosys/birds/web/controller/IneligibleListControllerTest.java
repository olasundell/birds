package se.atrosys.birds.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * TODO write comment
 */
public class IneligibleListControllerTest extends BaseTest {
	@Autowired IneligibleListController controller;

	@Test
	public void listShouldReturnValidModel() {
		ModelAndView modelAndView = controller.list();

		assertNotNull(modelAndView, "Expected an instance.");
        assertEquals(modelAndView.getViewName(), "listineligible", "Wrong view name");
        assertNotNull(modelAndView.getModel().get("ieModel"), "Model instance is null");
	}
}
