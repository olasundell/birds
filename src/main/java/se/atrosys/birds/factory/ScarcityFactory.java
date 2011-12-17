package se.atrosys.birds.factory;

import org.jsoup.nodes.Element;
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
		return ScarcityEnum.COMMON_OR_BREEDING;
	}
}
