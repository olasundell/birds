package se.atrosys.birds.model;

import javax.persistence.*;
import java.util.List;

/**
 * TODO write comment
 */
@Entity
@Table( name = "FAMILIES")
public class FamilyModel {
	@Id
	@Column(name = "FAMILY_NAME")
	private String family;
	@Column(name = "GROUP_NAME")
	private String group;

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public void setGroup(String group) {
		this.group = group;
	}
}
