package se.atrosys.birds.factory;

import org.springframework.stereotype.Component;
import se.atrosys.birds.model.RegionModel;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/17/11
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class RegionModelFactory {
	public RegionModel createModel(String regionName) {
		RegionModel regionModel = new RegionModel();

		regionModel.setName(regionName);

		return regionModel;
	}	
}
