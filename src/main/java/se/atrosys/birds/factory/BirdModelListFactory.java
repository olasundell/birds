package se.atrosys.birds.factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.FamilyModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 11/29/11
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BirdModelListFactory {
	@Autowired private BirdModelFactory birdModelFactory;
	@Autowired private FamilyModelFactory familyModelFactory;


	public List<BirdModel> scrapeFromAviBase(String fileName) throws IOException, CouldNotFindNamesElementException, NoSuchLanguageException {
        ArrayList<BirdModel> birdModels = new ArrayList<BirdModel>();

		File file = new File(fileName);
        
        Document doc = Jsoup.parse(file, "UTF-8", "http://avibase.bsc-eoc.org/");
        Element table = doc.getElementsByClass("AVBlist").get(3).child(0);
		FamilyModel currentFamily = null;

        for (Element bird: table.children()) {
			if (bird.getElementsByAttribute("colspan").size() == 0) {
				if (bird.getElementsByAttribute("valign").size() > 0) {
					currentFamily = familyModelFactory.createModel(bird);
				} else {
					BirdModel model = birdModelFactory.createModel(bird, null);
					model.setFamily(currentFamily);
					birdModels.add(model);
				}
			}
        }

        return birdModels;
    }

    public List<BirdModel> readFromFile(String fileName) throws IOException {
        ArrayList<BirdModel> birdModels = new ArrayList<BirdModel>();
        
        File file = new File(fileName);

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith(" ")) {
                // scientific name
            } else {
//                line.
            }
        }
        
        reader.close();

        return birdModels;
    }
}
