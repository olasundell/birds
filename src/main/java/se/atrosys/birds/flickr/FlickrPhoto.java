package se.atrosys.birds.flickr;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/10/11
 * Time: 4:36 PM
 * To change this template use File | Settings | File Templates.
 */
//@XmlRootElement(name = "photo")
public class FlickrPhoto {
	/*
		<photo id="6472803225" 
		owner="8751400@N06" 
		secret="5b11a7034c" 
		server="7165" 
		farm="8"
		title="39 P (100)"
		ispublic="1"
		isfriend="0"
		isfamily="0" />
	 */

	@XmlAttribute
	String id;
	@XmlAttribute
	String owner;
	@XmlAttribute
	String secret;
	@XmlAttribute
	String server;
	@XmlAttribute
	String farm;
	@XmlAttribute
	String title;
	
	public String getUrl() {
		return MessageFormat.format("http://farm{0}.staticflickr.com/{1}/{2}_{3}.jpg", farm, server, id, secret);
		/*
			or
		http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
			or
		http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{o-secret}_o.(jpg|gif|png)
		 */
	}
}
