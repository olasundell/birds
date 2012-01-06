package se.atrosys.birds.factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.birds.exception.CouldNotFindDetailsException;
import se.atrosys.birds.exception.CouldNotFindNamesElementException;
import se.atrosys.birds.exception.CouldNotFindSoundsException;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.flickr.FlickrPhotoList;
import se.atrosys.birds.flickr.FlickrService;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.RegionModel;
import se.atrosys.birds.service.LanguageService;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

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
	LanguageService nameService;
	@Autowired FlickrService flickrService;
	@Autowired private ScarcityFactory scarcityFactory;
	@Autowired SoundModelListFactory soundModelListFactory;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public BirdModel createModel(Element listingElement) throws NoSuchLanguageException, CouldNotFindDetailsException, JAXBException, CouldNotFindNamesElementException, CouldNotFindSoundsException {
		return createModel(listingElement, null);
	}

	public BirdModel createModel(Element listingElement, RegionModel regionModel) throws NoSuchLanguageException, CouldNotFindDetailsException, JAXBException, CouldNotFindNamesElementException {
        BirdModel birdModel = createInitialInstance(listingElement, regionModel);
		Element detailedElement = createDetailedElement(birdModel);

		Element namesElement = null;
		try {
			namesElement = findNamesElement(detailedElement);
		} catch (CouldNotFindNamesElementException e) {
			logger.error(String.format("Could not find names for %s", birdModel.getScientificName()));
			throw e;
		}
		enrichModelFromAvibase(birdModel, namesElement);
		enrichModelWithPhotos(birdModel);
		enrichmodelWithSound(birdModel);

        return birdModel;
    }

	private void enrichmodelWithSound(BirdModel birdModel) {
		try {
			birdModel.addSounds(soundModelListFactory.createList(birdModel));
		} catch (CouldNotFindSoundsException e) {
			logger.info(String.format("Could not add sounds to model for %s due to the following error: %s", birdModel.getScientificName(), e.getMessage()));
		}
	}

	private void enrichModelWithPhotos(BirdModel birdModel) throws JAXBException {
		FlickrPhotoList pictures = flickrService.getPictures(birdModel);

		birdModel.addPhotos(pictures);
	}

	private Element createDetailedElement(BirdModel birdModel) throws CouldNotFindDetailsException {
		String format = String.format("/home/ola/code/birds/species/%s", birdModel.getHref());
		File file = new File(format);
		try {
			return Jsoup.parse(file, "UTF-8").body();
		} catch (IOException e) {
			throw new CouldNotFindDetailsException(format);
		}
	}

	protected BirdModel createInitialInstance(Element birdElement, RegionModel regionModel) throws NoSuchLanguageException {
        BirdModel birdModel = new BirdModel();

        birdModel.setScientificName(birdElement.getElementsByTag("i").text());
        birdModel.setHref(birdElement.getElementsByAttribute("href").attr("href"));
		birdModel.putName(nameService.getLocaleForCountryDisplayName("English"), birdElement.getElementsByTag("td").get(0).text());
		birdModel.setScarcityForRegion(regionModel, scarcityFactory.getScarcity(birdElement));

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
		                logger.debug(String.format("Could not find language %s", currentLang));
	                }

                    currentLang = "";
                }
            }
        }
    }

    protected Element findNamesElement(Element element) throws CouldNotFindNamesElementException {
        for (Element e: element.getElementsByTag("b")) {
	        // TODO this should be a bit more precise, check for more languages than German
            Elements elements = e.getElementsContainingText("German:");
            if (elements.size() > 0) {
                return e.parent();
            }
        }

        throw new CouldNotFindNamesElementException(element.toString());
    }
}
