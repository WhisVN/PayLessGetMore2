package com.example.project.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "City")
@Table(name = "city", uniqueConstraints = { @UniqueConstraint(columnNames = { "cityName" }) } )
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cityId;

	@Column(length = 100, nullable = false)
	private String cityName;

	@OneToMany(
			fetch = FetchType.LAZY, 
			mappedBy = "city",
			cascade = CascadeType.ALL
	)
	private Set<District> districts = new HashSet<>();

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Set<District> getDistricts() {
		return districts;
	}

	public void setDistricts(Set<District> districts) {
		this.districts = districts;
	}
	
	public void addDistrict(District district) {
		districts.add(district);
		district.setCity(this);
    }

    public void removeDistrict(District district) {
    	districts.remove(district);
        district.setCity(null);
    }
    
	public City() {
	}

	public void update(City city) {
		this.cityName = city.cityName;
		this.districts =city.districts;
	}
}
