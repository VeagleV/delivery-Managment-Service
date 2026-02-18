package com.crm.delivery.core.repositories;

import com.crm.delivery.core.entities.Transit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransitRepository extends JpaRepository<Transit, Integer> {
    Transit findById(int id);
    Optional<Transit> findByRequestId(int requestId);
}
