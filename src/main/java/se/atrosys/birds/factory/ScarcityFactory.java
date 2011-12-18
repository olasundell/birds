package se.atrosys.birds.factory;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import se.atrosys.birds.model.ScarcityEnum;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/17/11
 * Time: 6:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ScarcityFactory {
	public ScarcityEnum getScarcity(Element birdElement) {
		Elements elements = birdElement.getElementsByTag("td");
		
		if (elements.size() != 3) {
			// hilfe!
			return ScarcityEnum.COMMON_OR_BREEDING;
		} else {
			if (elements.get(2).text().startsWith("Rare")) {
				return ScarcityEnum.RARE_ACCIDENTAL;
			}
		}

		return ScarcityEnum.COMMON_OR_BREEDING;
	}
}
