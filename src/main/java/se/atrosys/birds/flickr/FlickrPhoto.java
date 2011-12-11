package se.atrosys.birds.flickr;

import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.PhotoModel;

import javax.persistence.*;
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
public class FlickrPhoto implements PhotoModel {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getFarm() {
		return farm;
	}

	public void setFarm(String farm) {
		this.farm = farm;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FlickrPhoto that = (FlickrPhoto) o;

		if (farm != null ? !farm.equals(that.farm) : that.farm != null) return false;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
		if (secret != null ? !secret.equals(that.secret) : that.secret != null) return false;
		if (server != null ? !server.equals(that.server) : that.server != null) return false;
		if (title != null ? !title.equals(that.title) : that.title != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (owner != null ? owner.hashCode() : 0);
		result = 31 * result + (secret != null ? secret.hashCode() : 0);
		result = 31 * result + (server != null ? server.hashCode() : 0);
		result = 31 * result + (farm != null ? farm.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		return result;
	}
}
