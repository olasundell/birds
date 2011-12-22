package se.atrosys.birds.flickr;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.util.FileFetcher;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
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
	private final String FILE_URL = String.format("file:///home/ola/code/birds/flickr/{flickrmethod}-tags={tags}");
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private FileFetcher fileFetcher;

	public FlickrPhotoList getPictures(BirdModel model) throws JAXBException {
		List<Object> result = new ArrayList<Object>();

		Map<String, String> map = new HashMap<String, String>();

		map.put("flickrmethod", "flickr.photos.search");
		map.put("tags",model.getScientificName());

		File file = fileFetcher.fetchFile(map, FILE_URL, FLICKR_URL);

		Source source = new StreamSource(file);

		JAXBContext jaxbContext = JAXBContext.newInstance(FlickrResponseModel.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		FlickrResponseModel flickrResponseModel = (FlickrResponseModel) jaxbUnmarshaller.unmarshal(source);

		return flickrResponseModel.getPhotos();
	}
}
