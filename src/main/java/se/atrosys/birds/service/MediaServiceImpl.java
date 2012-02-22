package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.atrosys.birds.db.BirdPhotoDao;
import se.atrosys.birds.db.SoundDao;
import se.atrosys.birds.exception.CouldNotFindMediaException;
import se.atrosys.birds.model.BirdPhotoModel;
import se.atrosys.birds.model.MediaModel;
import se.atrosys.birds.model.MediaType;
import se.atrosys.birds.model.SoundModel;

/**
 * TODO write comment
 */

@Service("mediaService")
public class MediaServiceImpl implements MediaService {
	@Autowired BirdPhotoDao birdPhotoDao;
	@Autowired SoundDao soundDao;

    @Override
	public MediaModel setIneligible(String mediaId, String mediaType) throws CouldNotFindMediaException {
        return setEligibility(mediaId, MediaType.valueOf(mediaType), false);
    }
    
    @Override
    public MediaModel setEligibility(String mediaId, MediaType mediaType, boolean eligible) throws CouldNotFindMediaException {
		switch (mediaType) {
		case PHOTO:
			BirdPhotoModel birdPhotoModel = birdPhotoDao.findById(mediaId);
			if (birdPhotoModel == null) {
				throw new CouldNotFindMediaException(mediaId, MediaType.PHOTO);
			}
			birdPhotoModel.setEligible(eligible);
			birdPhotoDao.update(birdPhotoModel);
            return birdPhotoModel;
		case SOUND:
			SoundModel soundModel = soundDao.findById(mediaId);
			if (soundModel == null) {
				throw new CouldNotFindMediaException(mediaId, MediaType.SOUND);
			}
			soundModel.setEligible(false);
			soundDao.update(soundModel);
            return soundModel;
		default:
            throw new CouldNotFindMediaException(mediaId, mediaType);
		}
	}

	@Override
	public MediaModel setEligible(String mediaId, String mediaType) throws CouldNotFindMediaException {
		return setEligibility(mediaId, MediaType.valueOf(mediaType), true);
	}

	@Override
    public void update(MediaModel model) {
        switch (model.getType()) {
        case SOUND : 
            soundDao.update((SoundModel) model);
            break;
        case PHOTO : 
            birdPhotoDao.update((BirdPhotoModel) model);
            break;
        }
    }
}
