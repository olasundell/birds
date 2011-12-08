package se.atrosys.birds.factory;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
import org.slf4j.Logger;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.service.CountryNameService;
import se.atrosys.birds.model.BirdModel;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/3/11
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BirdModelFactory {
	@Autowired
	CountryNameService nameService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public BirdModel createModel(Element listingElement, Element detailedElement) throws CouldNotFindNamesElementException, NoSuchLanguageException {
        BirdModel birdModel = createInitialInstance(listingElement);
//        enrichModelFromAvibase(birdModel, findNamesElement(detailedElement));
        
        return birdModel;
    }

    protected BirdModel createInitialInstance(Element birdElement) throws NoSuchLanguageException {
        BirdModel birdModel = new BirdModel();

        birdModel.setScientificName(birdElement.getAllElements().get(4).text());
        birdModel.putName(nameService.getLocaleForCountryDisplayName("English"), birdElement.getAllElements().get(0).childNodes().get(0).attr("text"));
        birdModel.setHref(birdElement.getElementsByAttribute("href").attr("href"));
        return birdModel;
    }

    protected void enrichModelFromAvibase(BirdModel model, Element element) {
        String currentLang = "";

        for (Node node: element.childNodes()) {
            if (node.nodeName().equals("b")) {
                Element langElement = (Element)node;
                currentLang = langElement.text().replaceAll(":","").trim();
            } else if (node.nodeName().equals("#text")) {
                if (!currentLang.isEmpty()) {
	                Locale localeForCountryDisplayName = null;
	                try {
		                localeForCountryDisplayName = nameService.getLocaleForCountryDisplayName(currentLang);
		                // trim and remove nbsp
		                model.putName(localeForCountryDisplayName,
				                node.attr("text").replaceAll("[\\u00A0]", "").trim());
	                } catch (NoSuchLanguageException e) {
		                logger.info(String.format("Could not find language %s", currentLang));
	                }

                    currentLang = "";
                }
            }
        }
    }

    protected Element findNamesElement(Element element) throws CouldNotFindNamesElementException {
        for (Element e: element.getElementsByTag("b")) {
            Elements elements = e.getElementsContainingText("German:");
            if (elements.size() > 0) {
                return e.parent();
            }
        }

        throw new CouldNotFindNamesElementException(element.toString());
    }
}
