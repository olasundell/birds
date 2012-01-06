package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import se.atrosys.birds.BaseTest;
import se.atrosys.birds.exception.NoSuchLanguageException;

import java.util.Locale;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 9:56 AM
 * To change this template use File | Settings | File Templates.
 */
//@Test(groups = "system")
public class LanguageServiceTest extends BaseTest {
	@Autowired private LanguageService service;

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
		service.getLocaleForCountryDisplayName("Svalaboboy");
		assertTrue(false, "Exception wasn't thrown.");
	}
}
