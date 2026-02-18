package com.crm.delivery.core.repositories;

import com.crm.delivery.core.entities.Transit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransitRepository extends JpaRepository<Transit, Integer> {
    List<Transit> findByRequestId(int requestId);
}
