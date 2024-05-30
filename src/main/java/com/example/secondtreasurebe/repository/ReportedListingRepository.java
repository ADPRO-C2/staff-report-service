package com.example.secondtreasurebe.repository;

import com.example.secondtreasurebe.model.ReportedListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportedListingRepository extends JpaRepository<ReportedListing, String> {
    List<ReportedListing> findByUserId(int listingId);
}