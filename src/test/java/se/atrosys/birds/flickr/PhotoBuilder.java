package se.atrosys.birds.flickr;

import se.atrosys.birds.model.PhotoModel;

public class PhotoBuilder<T extends PhotoModel> {
	private static final String FARM = "farm";
	private static final String ID = "id";
	private static final String OWNER = "owner";
	private static final String SECRET = "secret";
	private static final String SERVER = "server";
	private static final String TITLE = "title";

	public T build(Class<T> aClass) throws IllegalAccessException, InstantiationException {
		PhotoModel e = aClass.newInstance();

		e.setId(ID);
		e.setFarm(FARM);
		e.setOwner(OWNER);
		e.setSecret(SECRET);
		e.setServer(SERVER);
		e.setTitle(TITLE);

		return (T) e;
	}
}