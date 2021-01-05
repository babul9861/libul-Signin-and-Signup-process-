package com.um.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.um.entities.City;

@Repository
public interface CityRepo extends JpaRepository<City,Integer>{
	
	List<City> findByStateId(Integer stateId);

}
