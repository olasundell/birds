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
public class SoundModel {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getURL() {
		return url;
	}
	
	public String getType() {
		return url.substring(url.length() - 3);
	}
}
