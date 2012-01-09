package se.atrosys.birds.db;

import se.atrosys.birds.model.SoundModel;

/**
 * TODO write comment
 */
public interface SoundDao {
	void update(SoundModel model);
	SoundModel findById(String mediaId);
}
