package se.atrosys.birds.web.model;

import se.atrosys.birds.model.BirdPhotoModel;
import se.atrosys.birds.model.SoundModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 1/14/12
 * Time: 9:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class IneligibleModel {
    private List<BirdPhotoModel> photos;
    private List<SoundModel> sounds;

    public IneligibleModel() {
        photos = new ArrayList<BirdPhotoModel>();
        sounds = new ArrayList<SoundModel>();
    }

    public List<BirdPhotoModel> getPhotos() {
        return photos;
    }

    public List<SoundModel> getSounds() {
        return sounds;
    }
}
