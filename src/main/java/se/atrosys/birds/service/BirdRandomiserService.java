package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.FamilyModel;
import se.atrosys.birds.model.PageModel;

import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/11/11
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BirdRandomiserService {
	@Autowired BirdService birdService;
	@Autowired FamilyService familyService;

	public PageModel randomiseBird() {
		PageModel pageModel = new PageModel();
		Random random = new Random();

		BirdModel model = birdService.getRandomBird();
		pageModel.setBirdModel(model);
		
		List<BirdModel> familyBirds = model.getFamily().getBirds();
		
		for (int i=0; i < 5 ; i++ ) {
			if (familyBirds.isEmpty()) {
				break;
			}

			BirdModel remove = familyBirds.remove(random.nextInt(familyBirds.size()));
			
			if (remove.equals(model)) {
				i--;
				continue;
			}
			
			pageModel.addSibling(remove);
		}

		return pageModel;
	}
}
