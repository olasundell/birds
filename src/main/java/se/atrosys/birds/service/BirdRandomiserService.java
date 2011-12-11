package se.atrosys.birds.service;

import org.springframework.stereotype.Service;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.PageModel;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/11/11
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BirdRandomiserService {
	public PageModel randomiseBird(BirdModel birdModel) {
		return new PageModel();
	}
}
