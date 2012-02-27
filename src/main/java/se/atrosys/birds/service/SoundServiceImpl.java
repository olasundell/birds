package se.atrosys.birds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.atrosys.birds.db.SoundDao;
import se.atrosys.birds.model.BirdModel;
import se.atrosys.birds.model.SoundModel;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/18/11
 * Time: 11:00 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("soundService")
public class SoundServiceImpl implements SoundService {
	@Autowired SoundDao dao;
	@Override
	public void enrichAll(List<BirdModel> list) {
		//To change body of created methods use File | Settings | File Templates.
	}

	@Override
	public SoundModel findById(String id) {
		return dao.findById(id);
	}

    @Override
	public Collection<? extends SoundModel> findAllIneligible() {
        return dao.findAllIneligible();
    }
}
