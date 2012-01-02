package se.atrosys.birds.model;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/23/11
 * Time: 11:07 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MediaModel {
	MediaType getType();
	boolean isEligible();
	String getId();
	
	public static enum MediaType {
		SOUND,
		PHOTO
	}
}
