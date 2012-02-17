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
    MediaModel setIneligible(String mediaId, MediaType mediaType) throws CouldNotFindMediaException;
    void update(MediaModel model);
}
