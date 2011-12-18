package se.atrosys.birds.model;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/17/11
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "REGIONAL_SCARCITY")
public class RegionalScarcityModel {
	@Id
	@GeneratedValue
	private int id;

	@OneToOne
	private BirdModel birdModel;
	@OneToOne
	private RegionModel regionModel;
	@Enumerated(EnumType.STRING)
	private ScarcityEnum scarcity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RegionModel getRegion() {
		return regionModel;
	}

	public void setScarcity(ScarcityEnum scarcity) {
		this.scarcity = scarcity;
	}

	public void setRegion(RegionModel regionModel) {
		this.regionModel = regionModel;
	}

	public void setBird(BirdModel bird) {
		this.birdModel = bird;
	}
}
