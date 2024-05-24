package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.Listing;
import com.example.secondtreasurebe.repository.ListingRepository;
import com.example.secondtreasurebe.repository.ReportedListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReportedListingService {
    @Autowired
    private ListingRepository listingRepository;
    @Autowired
    private ReportedListingRepository reportedListingRepository;

    public ResponseEntity<Object> getAllReportedListing() {
        return new ResponseEntity<>(reportedListingRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> add(String id) {
        if (listingRepository.existsById(id)){
            Listing listing = listingRepository.findById(id).get();
            reportedListingRepository.save(listing);
            return new ResponseEntity<>("The listing has been added", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("ERROR: Cannot add the listing", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> remove(String id) {
        if (reportedListingRepository.existsById(id)){
            reportedListingRepository.deleteById(id);
            return new ResponseEntity<>("The listing has been removed", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("ERROR: Cannot remove the listing", HttpStatus.NOT_FOUND);
        }
    }
}
