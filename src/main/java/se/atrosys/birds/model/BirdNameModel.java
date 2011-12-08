package se.atrosys.birds.model;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/8/11
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "BIRD_NAMES")
public class BirdNameModel {
	@Id
	@Column(name = "BIRD_ID")
	private String birdId;
	@Column(name = "BIRD_NAME")
	private String name;
	@Column(name = "LANG")
	private String lang;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
}
