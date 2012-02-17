package se.atrosys.birds.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import se.atrosys.birds.factory.BirdModelCollectionBuilder;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.MediaModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 1/22/12
 * Time: 9:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class StubMediaServiceBuilder {
    List<MediaModel> mediaModels = new ArrayList<MediaModel>();
    @Autowired BirdModelCollectionBuilder birdModelCollectionBuilder;

    public StubMediaServiceImpl build() {
        List<BirdModel> birdModels = birdModelCollectionBuilder.build();

        for (BirdModel birdModel: birdModels) {
            addAllModels(birdModel.getPhotos());
            addAllModels(birdModel.getSounds());
        }

        return new StubMediaServiceImpl(mediaModels);
    }
    
    public StubMediaServiceBuilder addAllModels(Collection<? extends MediaModel> collection) {
        mediaModels.addAll(collection);
        return this;
    }
}
