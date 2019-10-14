package com.soft.mikessolutions.rentservice.repositories;

import com.soft.mikessolutions.rentservice.entities.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
}
