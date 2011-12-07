package se.atrosys.birds.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.service.BirdServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/7/11
 * Time: 8:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class BirdControllerTest extends AbstractTest {
	public static final String HREF = "bar avibaseid=321";
	public static final String SCIENTIFIC_NAME = "Scifinameisch";
	@Autowired BirdController controller;
	private BirdModel birdModel;

	@BeforeMethod
	protected void setUp() throws Exception {
		birdModel = new BirdModel();
	}

	@Test
	public void controllerShouldControl() {
		ReflectionTestUtils.setField(controller, "service", new BirdServiceImpl() {
			public List<BirdModel> findAll() {
				ArrayList<BirdModel> birdModels = new ArrayList<BirdModel>();
				birdModel.setHref(HREF);
				birdModel.setScientificName(SCIENTIFIC_NAME);
				birdModels.add(birdModel);
				
				return birdModels;
			}
		});
		Model model = new ExtendedModelMap();

		BirdModel paramBirdModel = new BirdModel();
		Assert.assertEquals("random", controller.randomBird(paramBirdModel));
		Assert.assertEquals(paramBirdModel, birdModel);
		
	}
}
