package se.atrosys.birds.factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.birds.model.FamilyModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/8/11
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class FamilyModelListFactory {
	@Autowired FamilyModelFactory familyModelFactory;
	
	public Map<String, FamilyModel> scrapeFromAviBase(String fileName) throws IOException {
		Map<String, FamilyModel> map = new HashMap<String, FamilyModel>();

		File file = new File(fileName);
		Document doc = Jsoup.parse(file, "UTF-8", "http://avibase.bsc-eoc.org/");
		Element table = doc.getElementsByClass("AVBlist").get(3).child(0);

		for (Element bird: table.children()) {
			if (bird.getElementsByAttribute("valign").size() > 0) {
				FamilyModel model = familyModelFactory.createModel(bird);
				map.put(model.getFamily(), model);
			}
		}
		
		return map;
	}
}
