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
    public String toString() {
        return "BirdNameModel{" +
                "name='" + name + '\'' +
                ", languageModel=" + languageModel +
                '}';
    }
}
