package com.rentease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rentease.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
