package se.atrosys.birds.exception;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/9/11
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class CouldNotFindDetailsException extends Exception {
	public CouldNotFindDetailsException(String format) {
		super(format);
	}
}
