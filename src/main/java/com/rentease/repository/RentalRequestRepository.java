package com.rentease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rentease.model.RentalRequest;

@Repository
public interface RentalRequestRepository extends JpaRepository<RentalRequest, Long> {
}
