package se.atrosys.birds.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TODO write comment
 */
@Entity
@Table(name = "LANGUAGES")
public class LanguageModel {
	@Id
	String language;

	public void setLanguage(String lang) {
		this.language = lang;
	}

	public String getLanguage() {
		return language;
	}
}
