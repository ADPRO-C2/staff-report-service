package com.example.secondtreasurebe.repository;

import com.example.secondtreasurebe.model.TopUpTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UnverifiedTopUpTransactionRepository extends JpaRepository<TopUpTransaction, UUID> {
}
