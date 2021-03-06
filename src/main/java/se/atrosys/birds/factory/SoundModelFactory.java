package se.atrosys.birds.factory;

import org.springframework.stereotype.Component;
import se.atrosys.birds.model.SoundModel;

/**
 * Factory for a model of a bird sound
 */
@Component
public class SoundModelFactory {
	public SoundModel createModel(String href) {
		//To change body of created methods use File | Settings | File Templates.
		SoundModel soundModel = new SoundModel();
		soundModel.setURL(href);
		soundModel.setEligible(true);

		return soundModel;
	}
}
