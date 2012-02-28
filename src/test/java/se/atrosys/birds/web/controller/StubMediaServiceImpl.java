package se.atrosys.birds.web.controller;

import se.atrosys.birds.exception.CouldNotFindMediaException;
import se.atrosys.birds.model.BirdPhotoModel;
import se.atrosys.birds.model.MediaModel;
import se.atrosys.birds.model.MediaType;
import se.atrosys.birds.model.SoundModel;
import se.atrosys.birds.service.MediaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by IntelliJ IDEA.
* User: ola
* Date: 1/22/12
* Time: 9:17 AM
* To change this template use File | Settings | File Templates.
*/
//@Service("stubMediaService")
class StubMediaServiceImpl implements MediaService {
    Map<String, MediaModel> modelMap;
    int mediaId = 0;

    public StubMediaServiceImpl(List<MediaModel> mediaModels) {
        modelMap = new HashMap<String, MediaModel>();
        for (MediaModel model: mediaModels) {
            if (model.getId() == null || model.getId().isEmpty() || modelMap.containsKey(model.getId())) {
                switch (model.getType()) {
                case PHOTO :
                    ((BirdPhotoModel)model).setId(String.valueOf(mediaId++));
                    break;
                case SOUND :
                    ((SoundModel)model).setId(mediaId++);
                    break;
                }
            }

            modelMap.put(model.getId(), model);
        }
    }

    @Override
    public MediaModel setIneligible(String mediaId, String mediaType) {
        MediaModel mediaModel = modelMap.get(mediaId);
        mediaModel.setEligible(false);
        return mediaModel;
    }

    @Override
    public MediaModel setEligibility(String mediaId, MediaType mediaType, boolean eligible) throws CouldNotFindMediaException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

	@Override
	public MediaModel setEligible(String mediaId, String mediaType) throws CouldNotFindMediaException {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
    public void update(MediaModel model) {
    }

	public Map<String, MediaModel> getModelMap() {
		return modelMap;
	}
}
