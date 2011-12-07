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
		String[] text = bird.getElementsByTag("b").get(0).text().split(": ");
		
		model.setGroup(text[0]);
		model.setFamily(text[1]);

		return model;
	}
}