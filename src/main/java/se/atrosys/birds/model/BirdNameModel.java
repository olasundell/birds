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
@Table(name = "BIRD_NAMES", uniqueConstraints = @UniqueConstraint(columnNames = {"BIRD_ID", "BIRD_NAME", "LANGUAGE"}))
public class BirdNameModel {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BIRD_ID")
	private BirdModel bird;*/
	@Column(name = "BIRD_ID")
	private String birdId;
	@Column(name = "BIRD_NAME")
	private String name;
	@ManyToOne
	@JoinColumn(name = "LANGUAGE")
	private LanguageModel languageModel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LanguageModel getLang() {
		return languageModel;
	}
	
	public void setLang(String lang) {
		if (this.languageModel == null) {
			LanguageModel model = new LanguageModel();
			model.setLanguage(lang.toLowerCase());
			setLang(model);
		}
	}

	public void setLang(LanguageModel lang) {
		this.languageModel = lang;
	}

/*	public void setBird(BirdModel bird) {
		this.bird = bird;
	}*/

	public String getBirdId() {
		return birdId;
	}

	public void setBirdId(String birdId) {
		this.birdId = birdId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BirdNameModel that = (BirdNameModel) o;

		if (id != that.id) return false;
		if (birdId != null ? !birdId.equals(that.birdId) : that.birdId != null) return false;
		if (languageModel != null ? !languageModel.equals(that.languageModel) : that.languageModel != null)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (birdId != null ? birdId.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (languageModel != null ? languageModel.hashCode() : 0);
		return result;
	}
}
