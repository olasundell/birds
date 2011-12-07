package se.atrosys.birds.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 11/27/11
 * Time: 9:30 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table( name = "BIRDS")
public class BirdModel {
	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "SCIENTIFIC_NAME")
    private String scientificName;
	@Column(name = "HREF")
	private String href;
	@Transient
    private Map<Locale, String> nameLocaleMap;
/*	@OneToMany( mappedBy = "id", cascade = CascadeType.ALL)
	@MapKey(name = "language")*/
	@Transient
	private Map<String, String> nameStringMap;
	

    public BirdModel() {
        nameLocaleMap = new HashMap<Locale, String>();
	    nameStringMap = new HashMap<String, String>();
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getName(String lang) {
        return nameStringMap.get(lang);
    }
	
    public void putName(Locale lang, String name) {
        nameLocaleMap.put(lang, name);
	    nameStringMap.put(lang.getLanguage(), name);
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
		if (href == null || href.isEmpty() || !href.contains("avibaseid=")) {
			throw new IllegalArgumentException(String.format("%s isn't a legal href", href));
		}

        this.href = href;
		try {
			this.id = href.split("avibaseid=")[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			LoggerFactory.getLogger(this.getClass()).error(href, e);
		}
    }

	public String getId() {
		return id;
	}
}
