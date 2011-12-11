package se.atrosys.birds.flickr;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/11/11
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class FlickrPhotoList {
	@XmlElement(name = "photo")
	List<FlickrPhoto> photos;

	public FlickrPhotoList() {
		photos = new ArrayList<FlickrPhoto>();
	}

	public boolean isEmpty() {
		return photos.isEmpty();
	}

	public FlickrPhoto get(int i) {
		return photos.get(i);
	}

	public Collection getList() {
		return photos;
	}
}
