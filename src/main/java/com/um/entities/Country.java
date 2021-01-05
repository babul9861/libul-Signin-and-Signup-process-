package com.um.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "country_master")
public class Country {
	@Id
	private int countryId;
	private String countryName;
	
	

}
