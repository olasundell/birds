package se.atrosys.birds.model;

import org.hibernate.annotations.ForeignKey;

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
	@OneToMany(mappedBy = "family")
    @ForeignKey(name = "FK_FAMILY_BIRDS")
	private List<BirdModel> birds;
	@ManyToOne
	@JoinColumn(name="GROUP_NAME")
	private GroupModel group;

	public FamilyModel() {
		birds = new ArrayList<BirdModel>();
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public void addBird(BirdModel model) {
		birds.add(model);
	}

	public List<BirdModel> getBirds() {
		return birds;
	}

	public void setGroup(GroupModel group) {
		this.group = group;
	}

	public GroupModel getGroup() {
		return group;
	}
}
