package se.atrosys.birds.factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.birds.exception.CouldNotFindDetailsException;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.NoFamilyException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.FamilyModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	@Autowired private FamilyModelListFactory familyModelListFactory;
	@Autowired private FamilyModelFactory familyModelFactory;
	private Logger logger = LoggerFactory.getLogger(this.getClass());


	public List<BirdModel> scrapeFromAviBase(String fileName) throws IOException, CouldNotFindNamesElementException, NoSuchLanguageException, NoFamilyException, CouldNotFindDetailsException {
        ArrayList<BirdModel> birdModels = new ArrayList<BirdModel>();

		File file = new File(fileName);

		Map<String, FamilyModel> familyModelMap = familyModelListFactory.scrapeFromAviBase(fileName);
        
        Document doc = Jsoup.parse(file, "UTF-8", "http://avibase.bsc-eoc.org/");
        Element table = getTable(doc);
		FamilyModel currentFamily = null;

        for (Element bird: table.children()) {
	        if (bird.getElementsByAttribute("valign").size() > 0) {
		        String key = familyModelFactory.extractFamilyName(bird);
		        currentFamily = familyModelMap.get(key);
	        } else if (bird.getElementsByAttribute("colspan").size() == 0) {
		        BirdModel model = birdModelFactory.createModel(bird);
		        if (currentFamily == null) {
			        logger.error("Trying to create a bird without a family.");
		        } else {
			        model.setFamily(currentFamily);
			        currentFamily.addBird(model);
			        birdModels.add(model);
		        }
	        }
        }

        return birdModels;
    }

	public Element getTable(Document doc) {
		return doc.getElementsByClass("AVBlist").get(3).child(0);
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
