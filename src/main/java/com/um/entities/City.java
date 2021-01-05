package com.um.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "city_master")
public class City {
	
	@Id
	private int cityId;
	private String cityName;
	private int stateId;
	
	
}
