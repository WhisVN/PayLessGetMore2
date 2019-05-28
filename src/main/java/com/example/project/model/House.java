package com.example.project.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "House")
@Table(name = "house")
public class House {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long houseId;

	private String specificAddress;

	private Integer room;

	private String images;
	
	private Long vote;

	private Boolean status;

	private String note;

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "districtId", nullable = false)
	private District district;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="owner_id")
	private User owner;

	public House() {
	}

	public Long getVote() {
		return vote;
	}

	public void setVote(Long vote) {
		this.vote = vote;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getSpecificAddress() {
		return specificAddress;
	}

	public void setSpecificAddress(String specificAddress) {
		this.specificAddress = specificAddress;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(houseId, house.houseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseId);
    }


	public void update(House house) {
		this.specificAddress = house.specificAddress;
		this.room = house.room;
		this.images = house.images;
		this.vote = house.vote;
		this.status = house.status;
		this.note = house.note;
		
		this.district = house.district;
		
		this.owner = house.owner;
	}
}

