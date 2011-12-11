package se.atrosys.birds.flickr;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.AbstractTest;
import se.atrosys.birds.exception.NoSuchLanguageException;
import se.atrosys.birds.flickr.FlickrService;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.service.CountryNameService;

import javax.xml.bind.JAXBException;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/10/11
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class FlickrServiceTest extends AbstractTest {
	@Autowired CountryNameService nameService;
	@Autowired FlickrService service;

	@Test
	public void getPicturesShouldReturnList() throws NoSuchLanguageException, JAXBException {
		BirdModel model = new BirdModel();
		model.setScientificName("Dendrocygna viduata");
		model.putName(nameService.getLocaleForCountryDisplayName("English"), "White-faced Whistling-Duck");

		FlickrPhotoList list = service.getPictures(model);

		assertNotNull(list, "list is null");
		assertFalse(list.isEmpty(), "List is empty");

		FlickrPhoto flickrPhoto = list.get(0);
		assertNotNull(flickrPhoto.title, "First photo in list has null title");
		assertFalse(flickrPhoto.title.isEmpty(), "First photo in list lacks title");
		assertNotNull(flickrPhoto.farm, "First photo in list has null farm");
		assertFalse(flickrPhoto.farm.isEmpty(), "First photo in list lacks farm");
		assertNotNull(flickrPhoto.id, "First photo in list has null id");
		assertFalse(flickrPhoto.id.isEmpty(), "First photo in list lacks id");
		assertNotNull(flickrPhoto.owner, "First photo in list has null owner");
		assertFalse(flickrPhoto.owner.isEmpty(), "First photo in list lacks owner");
		assertNotNull(flickrPhoto.server, "First photo in list has null server");
		assertFalse(flickrPhoto.server.isEmpty(), "First photo in list lacks server");
		assertNotNull(flickrPhoto.getUrl(), "First photo in list has null url");
		assertFalse(flickrPhoto.getUrl().isEmpty(), "First photo in list lacks url");
	}
}
