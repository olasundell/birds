package se.atrosys.birds.service;

import org.springframework.stereotype.Component;
import se.atrosys.birds.exception.NoSuchLanguageException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 9:55 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CountryNameService {

	private final Map<String,Locale> languageNameMap;

	public CountryNameService() {
		languageNameMap = new HashMap<String, Locale>();
		for (Locale l: Locale.getAvailableLocales()) {
			languageNameMap.put(l.getDisplayLanguage().toLowerCase(), l);
		}
    }

    public Locale getLocaleForCountryDisplayName(String displayName) throws NoSuchLanguageException {
	    if (!languageNameMap.containsKey(displayName.toLowerCase())) {
		    throw new NoSuchLanguageException(displayName);
	    }
		return languageNameMap.get(displayName.toLowerCase());
    }
}
