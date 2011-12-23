package se.atrosys.birds.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.PageModel;

import java.util.List;
import java.util.Random;

@Component
public class PageModelFactory {
	public PageModelFactory() {
	}

	public PageModel createPageModel(BirdModel model) {
		Random random = new Random();

		PageModel pageModel = new PageModel();
		pageModel.setBirdModel(model);

		List<BirdModel> familyBirds = model.getFamily().getBirds();

		for (int i = 0; i < 5; i++) {
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

		// add correct bird as well!
		int index = 0;

		if (!pageModel.getSiblings().isEmpty()) {
			index = random.nextInt(pageModel.getSiblings().size());
		}

		pageModel.getSiblings().add(index, model);

		return pageModel;
	}
}