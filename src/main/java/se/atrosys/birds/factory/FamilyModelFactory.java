package se.atrosys.birds.factory;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import se.atrosys.birds.model.FamilyModel;

/**
 * TODO write comment
 */
@Component
public class FamilyModelFactory {
	public FamilyModel createModel(Element bird) {
		FamilyModel model = new FamilyModel();

		model.setGroup(extractGroupName(bird));
		model.setFamily(extractFamilyName(bird));

		return model;
	}

	public String extractGroupName(Element bird) {
		return extractNameArray(bird)[0];
	}

	public String extractFamilyName(Element bird) {
		return extractNameArray(bird)[1];
	}

	private String[] extractNameArray(Element bird) {
		return bird.getElementsByTag("b").get(0).text().split(": ");
	}
}