package com.danisanchez.PackMap.repository;

import com.danisanchez.PackMap.model.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelerRepository extends JpaRepository<Traveler, Long> {
    public Traveler findByEmail(String email);

}
