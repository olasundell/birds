package se.atrosys.birds.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.MediaModel;
import se.atrosys.birds.model.PageModel;

import java.util.ArrayList;
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
		pageModel.setCurrentMedia(getRandomMedia(model));

		List<BirdModel> familyBirds = model.getFamily().getBirds();

		for (int i = 0; i < 5; i++) {
			if (familyBirds.isEmpty()) {
				break;
			}

			BirdModel remove = familyBirds.remove(random.nextInt(familyBirds.size()));

			if (remove.equals(model) || !remove.getNameMap().containsKey("swedish")) {
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

	protected MediaModel getRandomMedia(BirdModel model) {
		List<MediaModel> mediaModels = new ArrayList<MediaModel>();
		
		mediaModels.addAll(model.getPhotos());
		mediaModels.addAll(model.getSounds());
		return mediaModels.get(new Random().nextInt(mediaModels.size()));
	}	
}