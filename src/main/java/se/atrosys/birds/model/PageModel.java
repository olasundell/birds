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
	private String previousAnswer;

	public MediaModel getCurrentMedia() {
		return currentMedia;
	}

	public void setCurrentMedia(MediaModel currentMedia) {
		this.currentMedia = currentMedia;
	}

	private MediaModel currentMedia;

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
		if (this.getSiblings().size() != pageModel.getSiblings().size()) {
			return false;
		}
		
		for (int i=0;i<getSiblings().size();i++) {
			if (!this.getSiblings().get(i).equals(pageModel.getSiblings().get(i))) {
				return false;
			}
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = birdModel != null ? birdModel.hashCode() : 0;
		result = 31 * result + (siblings != null ? siblings.hashCode() : 0);
		return result;
	}

	public void setPreviousAnswer(String previousAnswer) {
		this.previousAnswer = previousAnswer;
	}

	public boolean isSoundMedia() {
		return currentMedia.getType() == MediaType.SOUND;
	}
	
	public boolean isPictureMedia() {
		return currentMedia.getType() == MediaType.PHOTO;
	}

	public PhotoModel getCurrentPhoto() {
		return (PhotoModel)currentMedia;  //To change body of created methods use File | Settings | File Templates.
	}

	public SoundModel getCurrentSound() {
		return (SoundModel)currentMedia;  //To change body of created methods use File | Settings | File Templates.
	}
}
