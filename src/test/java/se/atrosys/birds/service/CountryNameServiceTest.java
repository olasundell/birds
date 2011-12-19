package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.exception.NoSuchLanguageException;

import java.util.Locale;

import static org.testng.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 9:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class CountryNameServiceTest extends BaseTest {
	@Autowired private CountryNameService service;

	@Test
	public void getLocaleForCountryDisplayNameShouldWork() throws NoSuchLanguageException {
		Locale locale = service.getLocaleForCountryDisplayName("German");

		assertNotNull(locale);
	}

	@Test
	public void getLocaleForUpperCaseCountryDisplayNameShouldWork() throws NoSuchLanguageException {
		Locale locale = service.getLocaleForCountryDisplayName("GERMAN");

		assertNotNull(locale);
	}

	@Test
	public void getLocaleForLowerCaseCountryDisplayNameShouldWork() throws NoSuchLanguageException {
		Locale locale = service.getLocaleForCountryDisplayName("german");

		assertNotNull(locale);
	}


	@Test(expectedExceptions = NoSuchLanguageException.class)
	public void getLocaleForErronousLangStringShouldThrowException() throws NoSuchLanguageException {
		Locale locale = service.getLocaleForCountryDisplayName("Svalaboboy");
	}
}
