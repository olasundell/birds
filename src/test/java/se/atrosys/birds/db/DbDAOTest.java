package se.atrosys.birds.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.model.BirdModel;

import static org.testng.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class DbDAOTest extends AbstractTest {
	public static final String HREF = "href";
	public static final String SCIENTIFIC_NAME = "scifiname";
	@Autowired DbDAO dao;
	private BirdModel birdModel;

	@BeforeClass
	protected void setUp() throws Exception {
		birdModel = new BirdModel() {{
			setHref(HREF);
			setScientificName(SCIENTIFIC_NAME);
		}};
	}

	@Test
	public void daoShouldStoreModel() {
		dao.storeModel(birdModel);
	}
	
	@Test
	public void daoShouldReadModel() {
		BirdModel model = dao.readModel(HREF);
		assertNotNull(model);
	}
}
