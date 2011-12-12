package se.atrosys.birds.model;

import se.atrosys.birds.flickr.FlickrPhoto;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/11/11
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "BIRD_PHOTOS")
public class BirdPhotoModel implements PhotoModel {
	@Id
	@Column
	private String id;
	@Column
	private String owner;
	@Column
	private String secret;
	@Column
	private String server;
	@Column
	private String farm;
	@Column
	private String title;
	@ManyToOne
	@JoinColumn(name = "BIRD_ID")
	private BirdModel bird;

	public BirdPhotoModel() {}

	public BirdPhotoModel(FlickrPhoto photo) {
		setFarm(photo.getFarm());
		setId(photo.getId());
		setOwner(photo.getOwner());
		setSecret(photo.getSecret());
		setServer(photo.getServer());
		setTitle(photo.getTitle());
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

	public void setBirdModel(BirdModel birdModel) {
		this.bird = birdModel;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BirdPhotoModel that = (BirdPhotoModel) o;

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
