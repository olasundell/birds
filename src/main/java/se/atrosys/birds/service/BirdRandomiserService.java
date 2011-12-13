package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.atrosys.birds.factory.PageModelFactory;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.PageModel;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/11/11
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BirdRandomiserService {
	@Autowired BirdService birdService;

	public BirdModel randomiseBird() {
		return birdService.getRandomBird();
	}
}
