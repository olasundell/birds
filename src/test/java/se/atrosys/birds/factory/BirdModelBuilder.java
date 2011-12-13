package se.atrosys.birds.factory;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.FamilyModel;

public class BirdModelBuilder {

	private final BirdModel birdModel;

	public BirdModelBuilder() {
		birdModel = new BirdModel();
	}

	public BirdModel build() {
		FamilyModel family = new FamilyModel();
		birdModel.setFamily(family);
		birdModel.setHref("fooavibaseid=123");

		family.addBird(birdModel);
		family.addBird(new BirdModel());
		family.addBird(new BirdModel());
		return birdModel;
	}

	public BirdModelBuilder setHref(String href) {
		birdModel.setHref(href);
		
		return this;  //To change body of created methods use File | Settings | File Templates.
	}

	public BirdModelBuilder setScientificName(String scientificName) {
		birdModel.setScientificName(scientificName);

		return this;
	}	
}