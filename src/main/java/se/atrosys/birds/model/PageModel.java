package se.atrosys.birds.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/11/11
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class PageModel {
	private BirdModel birdModel;
	private List<BirdModel> siblings;
	
	public PageModel() {
		siblings = new ArrayList<BirdModel>();
	}

	public BirdModel getBirdModel() {
		return birdModel;
	}

	public void setBirdModel(BirdModel birdModel) {
		this.birdModel = birdModel;
	}

	public void addSibling(BirdModel sibling) {
		siblings.add(sibling);
	}

	public List<BirdModel> getSiblings() {
		return siblings;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PageModel pageModel = (PageModel) o;

		if (birdModel != null ? !birdModel.equals(pageModel.birdModel) : pageModel.birdModel != null) return false;
		if (siblings != null ? !siblings.equals(pageModel.siblings) : pageModel.siblings != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = birdModel != null ? birdModel.hashCode() : 0;
		result = 31 * result + (siblings != null ? siblings.hashCode() : 0);
		return result;
	}
}
