package com.example.ibmvacationrentalapi.repository;

import com.example.ibmvacationrentalapi.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Integer> {

    Optional<UserProfile> findByEmail(String email);

    Optional<UserProfile> findByName(String name);
}
