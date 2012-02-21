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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RegionalScarcityModel that = (RegionalScarcityModel) o;

		if (id != that.id) return false;
		if (birdModel != null ? !birdModel.equals(that.birdModel) : that.birdModel != null) return false;
		if (regionModel != null ? !regionModel.equals(that.regionModel) : that.regionModel != null) return false;
		if (scarcity != that.scarcity) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (birdModel != null ? birdModel.hashCode() : 0);
		result = 31 * result + (regionModel != null ? regionModel.hashCode() : 0);
		result = 31 * result + (scarcity != null ? scarcity.hashCode() : 0);
		return result;
	}
}
