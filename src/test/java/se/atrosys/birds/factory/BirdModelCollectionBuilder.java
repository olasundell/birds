package se.atrosys.birds.factory;

import org.springframework.stereotype.Component;
import se.atrosys.birds.model.BirdModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 1/22/12
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BirdModelCollectionBuilder {
    public static final String HREF = "bar avibaseid=321";
    public static final String SCIENTIFIC_NAME = "Scifinameisch";
    List<BirdModel> lastCollection;

    public List<BirdModel> build() {
        List<BirdModel> list = new ArrayList<BirdModel>();
        list.add(new BirdModelBuilder().setHref(HREF).setScientificName(SCIENTIFIC_NAME).build());
        list.add(new BirdModelBuilder().setHref("ladlas avibaseid=1231231").setScientificName("foobar").build());
        
        lastCollection = list;
        
        return list;
    }
    
    public List<BirdModel> getLastCollection() {
        return lastCollection;
    }
}
