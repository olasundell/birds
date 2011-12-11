package se.atrosys.birds.flickr;

import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import se.atrosys.birds.model.BirdModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/10/11
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class FlickrService {
	private final String APIKEY = "2ae735c1073a6524c6af82dd4a0da37c";
	private final String SECRET = "43bbaa4345802f59";
	private final String FLICKR_URL = String.format("http://api.flickr.com/services/rest/?api_key=%s&method={flickrmethod}&tags={tags}", APIKEY);

	public FlickrPhotoList getPictures(BirdModel model) throws JAXBException {
		List<Object> result = new ArrayList<Object>();

		Map<String, String> map = new HashMap<String, String>();

		map.put("flickrmethod", "flickr.photos.search");
		map.put("tags",model.getScientificName());

		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getMessageConverters().add(new FlickrResponseModelHttpMessageConverter());
		restTemplate.getMessageConverters().add(new SourceHttpMessageConverter<Source>());
//		FlickrResponseModel response = restTemplate.getForObject(FLICKR_URL, FlickrResponseModel.class, map);
		Source source = restTemplate.getForObject(FLICKR_URL, Source.class, map);

		JAXBContext jaxbContext = JAXBContext.newInstance(FlickrResponseModel.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		FlickrResponseModel flickrResponseModel = (FlickrResponseModel) jaxbUnmarshaller.unmarshal(source);

/*		source = restTemplate.getForObject(FLICKR_URL, Source.class, map);
		jaxbContext = JAXBContext.newInstance(FlickrResponseModel.class, Rsp.class);
		jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Rsp rsp = (Rsp) jaxbUnmarshaller.unmarshal(source);
		FlickrResponseModel flickrResponseModel = (FlickrResponseModel) jaxbUnmarshaller.unmarshal(source);*/

		return flickrResponseModel.getPhotos();
	}

}
