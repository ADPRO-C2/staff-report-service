package com.example.secondtreasurebe.repository;

import com.example.secondtreasurebe.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportedListingRepository extends JpaRepository<Listing, String> {
    List<Listing> findByUserId(int listingId);
}