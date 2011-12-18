package se.atrosys.birds.flickr;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;
import se.atrosys.birds.model.BirdModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URI;
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
	private final HttpClient httpClient;

	public FlickrService() {
		httpClient = new DefaultHttpClient();
	}

	public FlickrPhotoList getPictures(BirdModel model) throws JAXBException {
		List<Object> result = new ArrayList<Object>();

		Map<String, String> map = new HashMap<String, String>();

		map.put("flickrmethod", "flickr.photos.search");
		map.put("tags",model.getScientificName());

		UriTemplate uriTemplate = new UriTemplate(FILE_URL);
		URI fileUri = uriTemplate.expand(map);
		File file = new File(fileUri);
		if (!file.exists()) {
			try {
//				Thread.sleep(100);
				// TODO rewrite this using Apache stuff so we can use a keep-alive connection, this stuff is horrible.
				URI uri = new UriTemplate(FLICKR_URL).expand(map);
				HttpGet httpget = new HttpGet(uri);
				HttpResponse response = httpClient.execute(httpget);
				HttpEntity entity = response.getEntity();

				FileWriter writer = new FileWriter(file);
				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
				String line = null;
				while ((line = reader.readLine())!= null) {
					writer.write(line);
				}
				
				writer.close();
				reader.close();

				file = new File(fileUri);

			} catch (IOException e) {
				logger.error("", e);
			} /*catch (InterruptedException e) {
				logger.error("", e);
			}*/
		}

/*		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new SourceHttpMessageConverter<Source>());
		Source source = restTemplate.getForObject(FILE_URL, Source.class, map);*/
		Source source = new StreamSource(file);

		JAXBContext jaxbContext = JAXBContext.newInstance(FlickrResponseModel.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		FlickrResponseModel flickrResponseModel = (FlickrResponseModel) jaxbUnmarshaller.unmarshal(source);

		return flickrResponseModel.getPhotos();
	}

}
