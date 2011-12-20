package se.atrosys.birds.factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import se.atrosys.birds.exception.CouldNotFindSoundsException;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.SoundModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/18/11
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class SoundModelListFactory {
	public List<SoundModel> createList(BirdModel birdModel) throws CouldNotFindSoundsException {
		List<SoundModel> soundModels = new ArrayList<SoundModel>();
		String format = String.format("/home/ola/code/birds/sounds/%s", birdModel.getScientificName().replace(' ', '-'));

		File file = new File(format);
		Element element = null;

		try {
			element = Jsoup.parse(file, "UTF-8").body();
		} catch (IOException e) {
			throw new CouldNotFindSoundsException(format);
		}
		
		Elements select = element.select("table.results");
		
		if (select.isEmpty()) {
			throw new CouldNotFindSoundsException("Can't find the results table in the markup, it isn't there!");
		} else if (select.size() > 1) {
			throw new CouldNotFindSoundsException("Can't find the results table in the markup, too many tables found!");
		}
		
		Element table = select.get(0);

		extractModels(soundModels, table, "mp3");
		extractModels(soundModels, table, "ogg");

		return soundModels;
	}

	private void extractModels(List<SoundModel> soundModels, Element table, String match) {
		Elements mp3List = table.getElementsByAttributeValueContaining("href", match);

		for (Element mp3: mp3List) {
			SoundModel soundModel = new SoundModel();
			soundModel.setURL(mp3.attr("href"));

			soundModels.add(soundModel);
			// TODO write factory
//			soundModelFactory.createModel(mp3.attr("href"));
		}
	}
}
