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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		GroupModel that = (GroupModel) o;

		if (families != null ? !families.equals(that.families) : that.families != null) return false;
		if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = groupName != null ? groupName.hashCode() : 0;
		result = 31 * result + (families != null ? families.hashCode() : 0);
		return result;
	}
}
