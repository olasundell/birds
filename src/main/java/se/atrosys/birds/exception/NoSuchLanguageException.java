package se.atrosys.birds.exception;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class NoSuchLanguageException extends Exception {
	public NoSuchLanguageException(String displayName) {
		super(displayName);
	}
}
