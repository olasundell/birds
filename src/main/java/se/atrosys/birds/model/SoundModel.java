package se.atrosys.birds.model;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/18/11
 * Time: 9:14 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SOUNDS")
public class SoundModel implements MediaModel {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String url;
	@Column(nullable = false)
	private boolean eligible = true;

	public void setId(int id) {
		this.id = id;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getURL() {
		return url;
	}
	
	public String getFileEnding() {
		return url.substring(url.length() - 3);
	}

	public MediaType getType() {
		return MediaType.SOUND;
	}

	public boolean isEligible() {
		return eligible;
	}

	public void setEligible(boolean eligible) {
		this.eligible = eligible;
	}

	public String getId() {
		return String.valueOf(id);  //To change body of implemented methods use File | Settings | File Templates.
	}
}
