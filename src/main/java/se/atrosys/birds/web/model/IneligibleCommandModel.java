package se.atrosys.birds.web.model;

import se.atrosys.birds.model.MediaModel;

/**
* Created by IntelliJ IDEA.
* User: ola
* Date: 1/14/12
* Time: 9:38 AM
* To change this template use File | Settings | File Templates.
*/
public class IneligibleCommandModel {
    private MediaModel mediaModel;

    public void setMediaModel(MediaModel mediaModel) {
        this.mediaModel = mediaModel;
    }

    public String getMediaId() {
        return mediaModel.getId();
    }

    public String getMediaType() {
        return mediaModel.getType().name();
    }
}
