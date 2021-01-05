package com.um.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.um.entities.State;

@Repository
public interface StateRepo extends JpaRepository<State, Integer>{
	
	List<State> findByCountryId(Integer countryId );

}
