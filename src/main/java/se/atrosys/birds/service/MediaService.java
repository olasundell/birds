package se.atrosys.birds.service;

import org.springframework.stereotype.Service;
import se.atrosys.birds.exception.CouldNotFindMediaException;

/**
 * TODO write comment
 */
public interface MediaService {
	void setIneligible(String mediaId, String mediaType) throws CouldNotFindMediaException;
}
