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
}
