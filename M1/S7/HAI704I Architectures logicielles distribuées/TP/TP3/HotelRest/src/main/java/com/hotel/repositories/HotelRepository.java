package com.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.models.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{
	
}
