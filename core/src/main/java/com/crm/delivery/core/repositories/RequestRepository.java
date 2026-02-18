package com.crm.delivery.core.repositories;

import com.crm.delivery.core.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    Request findById(int id);
    Optional<Request> findAllByUserId(int userId);
}
