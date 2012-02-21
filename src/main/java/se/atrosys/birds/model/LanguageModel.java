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

    @Override
    public String toString() {
        return "LanguageModel{" +
                "language='" + language + '\'' +
                '}';
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		LanguageModel that = (LanguageModel) o;

		if (language != null ? !language.equals(that.language) : that.language != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return language != null ? language.hashCode() : 0;
	}
}
