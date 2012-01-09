package se.atrosys.birds.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import se.atrosys.birds.db.BirdPhotoDao;

/**
* TODO write comment
*/
public enum MediaType {
	SOUND,
	PHOTO
}
