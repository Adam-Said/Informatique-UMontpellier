package com.hai818i.tp4.repositories;

import com.hai818i.tp4.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User,Long> { }
