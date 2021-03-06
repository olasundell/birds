package se.atrosys.birds.factory;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.birds.model.FamilyModel;
import se.atrosys.birds.model.GroupModel;

/**
 * Creates a family model on demand, also contains
 */
@Component
public class FamilyModelFactory {
	@Autowired GroupModelFactory groupModelFactory;

	public FamilyModel createModel(Element bird) {
		FamilyModel model = new FamilyModel();

		model.setGroup(extractGroupModel(bird));
		model.setFamily(extractFamilyName(bird));

		return model;
	}

	private GroupModel extractGroupModel(Element bird) {
		return groupModelFactory.createModel(extractNameArray(bird)[0]);
	}

	String extractFamilyName(Element bird) {
		return extractNameArray(bird)[1];
	}

	private String[] extractNameArray(Element bird) {
		return bird.getElementsByTag("b").get(0).text().split(": ");
	}
}