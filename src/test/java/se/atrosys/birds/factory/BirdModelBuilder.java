package se.atrosys.birds.factory;

import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.FamilyModel;

public class BirdModelBuilder {

	private final BirdModel birdModel;
	private String scientificName;
	private String href;

	public BirdModelBuilder() {
		birdModel = new BirdModel();
		scientificName = "Dendrocygna viduata";
		href = "species.jsp?avibaseid=A534AFEA126DBD62";
	}

	public BirdModel build() {
		FamilyModel family = new FamilyModel();
		birdModel.setFamily(family);

		birdModel.setHref(href);
		birdModel.setScientificName(scientificName);
		family.addBird(birdModel);
		family.addBird(new BirdModel());
		family.addBird(new BirdModel());
		return birdModel;
	}

	public BirdModelBuilder setHref(String href) {
		this.href = href;

		return this;  //To change body of created methods use File | Settings | File Templates.
	}

	public BirdModelBuilder setScientificName(String scientificName) {
		this.scientificName = scientificName;

		return this;
	}	
}