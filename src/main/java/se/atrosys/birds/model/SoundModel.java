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
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean eligible;

/*	public int getId() {
		return id;
	}*/

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SoundModel that = (SoundModel) o;

		if (eligible != that.eligible) return false;
		if (id != that.id) return false;
		if (url != null ? !url.equals(that.url) : that.url != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (url != null ? url.hashCode() : 0);
		result = 31 * result + (eligible ? 1 : 0);
		return result;
	}
}
