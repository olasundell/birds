package se.atrosys.birds.factory;

import org.springframework.stereotype.Component;
import se.atrosys.birds.model.GroupModel;

/**
 * TODO write comment
 */
@Component
public class GroupModelFactory {
	public GroupModel createModel(String groupName) {
		GroupModel model = new GroupModel();
		model.setGroupName(groupName);

		return model;
	}
}
