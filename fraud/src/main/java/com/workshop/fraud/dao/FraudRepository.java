package com.workshop.fraud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudRepository extends JpaRepository<Fraud, Integer > {
    Fraud findByCustomerId(Integer customerId);
    boolean existsByCustomerId(Integer customerId);
}