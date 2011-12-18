package se.atrosys.birds.flickr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
* Created by IntelliJ IDEA.
* User: ola
* Date: 12/10/11
* Time: 5:40 PM
* To change this template use File | Settings | File Templates.
*/
class FlickrResponseModelHttpMessageConverter implements HttpMessageConverter<FlickrResponseModel> {
	List <MediaType> mediaTypes;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public FlickrResponseModelHttpMessageConverter() {
		mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.parseMediaType("text/xml;charset=utf-8"));
	}

	@Override
	public boolean canRead(Class<?> aClass, MediaType mediaType) {
		return (aClass == FlickrResponseModel.class && mediaTypes.contains(mediaType));
	}

	@Override
	public boolean canWrite(Class<?> aClass, MediaType mediaType) {
		return (aClass == FlickrResponseModel.class && mediaTypes.contains(mediaType));
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return mediaTypes;
	}

	@Override
	public FlickrResponseModel read(Class<? extends FlickrResponseModel> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
		logger.info(String.format("read called with message %s", httpInputMessage.toString()));
		FlickrResponseModel flickrResponseModel = new FlickrResponseModel();
		
		String xml = extractXml(httpInputMessage);

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(FlickrResponseModel.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//			flickrResponseModel = (FlickrResponseModel) jaxbUnmarshaller.unmarshal();
		} catch (JAXBException e) {
			logger.error("Error occurred", e);
		}

		String foo = "bar";

		return flickrResponseModel;
	}

	private String extractXml(HttpInputMessage httpInputMessage) throws IOException {
		String xml;InputStream is = httpInputMessage.getBody();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuilder stringBuilder = new StringBuilder();

		while ((line=br.readLine())!=null) {
			stringBuilder.append(line);
		}

		xml = stringBuilder.toString();
		return xml;
	}

	@Override
	public void write(FlickrResponseModel flickrResponseModel, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
		logger.info("write called");
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
