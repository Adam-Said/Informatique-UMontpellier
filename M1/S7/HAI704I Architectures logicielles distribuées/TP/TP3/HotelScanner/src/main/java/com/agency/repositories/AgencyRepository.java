package com.agency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agency.models.Agency;

public interface AgencyRepository extends JpaRepository<Agency, Long>{
	
}
