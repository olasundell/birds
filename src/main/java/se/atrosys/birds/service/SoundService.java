package se.atrosys.birds.service;

import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.SoundModel;

import java.util.Collection;
import java.util.List;

/**
 * TODO write comment
 */
public interface SoundService {
	void enrichAll(List<BirdModel> list);

	SoundModel findById(String id);

	Collection<? extends SoundModel> findAllIneligible();
}
