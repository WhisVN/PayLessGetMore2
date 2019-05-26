package com.example.project.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "District")
@Table(name = "district")
public class District {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long districtId;

	@Column(length = 100)
	private String districtName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cityId", nullable = false)
	private City city;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			mappedBy = "district",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private Set<House> houses = new HashSet<>();

	public District() {
	}

	public District(String districtName) {
		super();
		this.districtName = districtName;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
    public Set<House> getHouses() {
		return houses;
	}

	public void setHouses(Set<House> houses) {
		this.houses = houses;
	}
	
	public void addHouse(House house) {
		this.houses.add(house);
		house.setDistrict(this);
	}
	
	public void removeHouse(House house) {
		this.houses.remove(house);
		house.setDistrict(null);
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return Objects.equals(districtId, district.districtId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(districtId);
    }

}
