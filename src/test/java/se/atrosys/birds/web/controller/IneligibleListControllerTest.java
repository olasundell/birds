package se.atrosys.birds.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.model.BirdPhotoModel;
import se.atrosys.birds.web.model.IneligibleModel;

import java.util.List;

import static org.testng.Assert.*;

/**
 * TODO write comment
 */
public class IneligibleListControllerTest extends BaseTest {
	@Autowired IneligibleListController controller;

	@Test
	public void controllerShouldSetView() {
		ModelAndView modelAndView = controller.list();

		assertNotNull(modelAndView, "Expected an instance.");
        assertEquals(modelAndView.getViewName(), "listineligible", "Wrong view name");
    }
    
    @Test
    public void controllerShouldSetModel() {
        ModelAndView modelAndView = controller.list();

        assertNotNull(modelAndView, "Expected an instance.");
        assertNotNull(modelAndView.getModel().get("ieModel"), "Model instance is null");
	}
    
    @Test
    public void modelShouldContainPhotos() {
        ModelAndView modelAndView = controller.list();

        assertNotNull(modelAndView, "Expected an instance.");
        IneligibleModel model = (IneligibleModel) modelAndView.getModel().get("ieModel");
        assertNotNull(model, "Model instance is null");

        List<BirdPhotoModel> list = model.getPhotos();
        assertNotNull(list, "Bird photo model list is null");
        assertFalse(list.isEmpty(), "Bird photo model list is empty");
    }
}
