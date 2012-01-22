package se.atrosys.birds.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.model.BirdPhotoModel;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 1/22/12
 * Time: 6:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class BirdPhotoDaoImplTest extends BaseTest {
    @Autowired BirdPhotoDao dao;
    BirdPhotoModel model;
    
    @BeforeMethod
    public void setAModelAsIneligible() {
        List<BirdPhotoModel> all = dao.findAll();
        model = null;
        for (BirdPhotoModel m: all) {
            if (m.isEligible()) {
                model = m;
                break;
            }
        }

        assertNotNull(model, "Could not find an eligible model");

        model.setEligible(false);
        dao.update(model);
    }
    
    @AfterMethod
    public void resetModel() {
        model.setEligible(true);
        dao.update(model);
    }

    @Test
    public void shouldGetIneligibleModels() {
        List<BirdPhotoModel> list = dao.findAllIneligible();

        assertFalse(list.isEmpty());
    }
}
