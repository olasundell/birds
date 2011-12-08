package se.atrosys.birds.model;

import org.apache.commons.collections.list.AbstractLinkedList;

import javax.persistence.*;
import java.util.ArrayList;
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
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "family")
	@OneToMany(mappedBy = "family")
	private List<BirdModel> birds;

	public FamilyModel() {
		birds = new ArrayList<BirdModel>();
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public void addBird(BirdModel model) {
		birds.add(model);
	}

	public String getGroup() {
		return group;  //To change body of created methods use File | Settings | File Templates.
	}
}
