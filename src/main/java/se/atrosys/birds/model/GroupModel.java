package se.atrosys.birds.model;

import javax.persistence.*;
import java.util.List;

/**
 * TODO write comment
 */
@Entity
@Table(name = "GROUPS")
public class GroupModel {
	@Id
	@Column(name ="GROUP_NAME")
	String groupName;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<FamilyModel> families;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<FamilyModel> getFamilies() {
		return families;
	}
}
