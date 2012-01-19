package se.atrosys.birds.db;

import se.atrosys.birds.model.SoundModel;

import java.util.Collection;

/**
 * TODO write comment
 */
public interface SoundDao {
	void update(SoundModel model);
	SoundModel findById(String mediaId);
    Collection<? extends SoundModel> findAllIneligible();
}
