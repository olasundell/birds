package se.atrosys.birds.model;

import org.hibernate.annotations.CollectionOfElements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.atrosys.birds.flickr.FlickrPhoto;
import se.atrosys.birds.flickr.FlickrPhotoList;

import java.util.*;
import javax.persistence.*;
import javax.persistence.metamodel.EntityType;

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

	@ManyToOne
	@JoinColumn(name = "FAMILY_NAME")
	private FamilyModel family;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<BirdNameModel> birdNameModels;
	@OneToMany(cascade = CascadeType.ALL)
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<BirdPhotoModel> birdPhotos;

	@Transient
	private Map<Locale, String> nameLocaleMap;
	@Transient
	private Map<String, String> nameStringMap;

	public BirdModel() {
        nameLocaleMap = new HashMap<Locale, String>();
	    nameStringMap = new HashMap<String, String>();
		birdNameModels = new ArrayList<BirdNameModel>();
		birdPhotos = new ArrayList();
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

	    BirdNameModel birdNameModel = new BirdNameModel();
	    birdNameModel.setLang(lang.getDisplayLanguage());
	    birdNameModel.setName(name);
	    birdNameModel.setBird(this);

	    birdNameModels.add(birdNameModel);
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
			throw e;
		}
    }

	public String getId() {
		return id;
	}

	public void setFamily(FamilyModel family) {
		this.family = family;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BirdModel birdModel = (BirdModel) o;

		if (href != null ? !href.equals(birdModel.href) : birdModel.href != null) return false;
		if (id != null ? !id.equals(birdModel.id) : birdModel.id != null) return false;
		if (scientificName != null ? !scientificName.equals(birdModel.scientificName) : birdModel.scientificName != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (scientificName != null ? scientificName.hashCode() : 0);
		result = 31 * result + (href != null ? href.hashCode() : 0);
		return result;
	}

	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="FAMILY_NAME", table = "FAMILIES")
	public FamilyModel getFamily() {
		return family;
	}

	public List<BirdNameModel> getNames() {
		return birdNameModels;
	}
	
	public void setNames(List<BirdNameModel> birdNameModels) {
		this.birdNameModels = birdNameModels;
	}

	public void addPhotos(FlickrPhotoList pictures) {
		birdPhotos.addAll(pictures.getList());
	}

	public List getPhotos() {
		return birdPhotos;
	}

	public void setPhotos(List<BirdPhotoModel> photos) {
		this.birdPhotos = photos;
		for (BirdPhotoModel birdPhotoModel: birdPhotos) {
			birdPhotoModel.setBirdModel(this);
		}
	}
}
