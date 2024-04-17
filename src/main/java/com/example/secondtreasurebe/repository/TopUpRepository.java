package com.example.secondtreasurebe.repository;

import com.example.secondtreasurebe.model.TopUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopUpRepository extends JpaRepository<TopUp, Integer> {
}