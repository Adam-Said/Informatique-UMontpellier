package com.hai818i.tp4.repositories;

import com.hai818i.tp4.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository
        extends JpaRepository<Location,Long> {}
