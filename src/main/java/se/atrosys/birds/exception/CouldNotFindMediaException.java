package se.atrosys.birds.exception;

import se.atrosys.birds.model.MediaType;

/**
 * TODO write comment
 */
public class CouldNotFindMediaException extends Exception {
	public CouldNotFindMediaException(String mediaId, MediaType mediaType) {
		super(String.format("Could not find media type %s with id %s", mediaType.toString(), mediaId));
	}
}
