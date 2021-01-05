package com.um.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.um.entities.Country;

@Repository
public interface CountryRepo extends JpaRepository<Country, Integer> {
	

}