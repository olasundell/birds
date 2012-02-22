package se.atrosys.birds.service;

import org.springframework.stereotype.Service;
import se.atrosys.birds.exception.CouldNotFindMediaException;
import se.atrosys.birds.model.MediaModel;
import se.atrosys.birds.model.MediaType;

/**
 * TODO write comment
 */
public interface MediaService {
	MediaModel setIneligible(String mediaId, String mediaType) throws CouldNotFindMediaException;
    MediaModel setEligibility(String mediaId, MediaType mediaType, boolean eligible) throws CouldNotFindMediaException;
	MediaModel setEligible(String mediaId, String mediaType) throws CouldNotFindMediaException;
	void update(MediaModel model);
}
