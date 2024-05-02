package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.TopUp;
import com.example.secondtreasurebe.repository.AcceptedTopUpRepository;
import com.example.secondtreasurebe.repository.RejectedTopUpRepository;
import com.example.secondtreasurebe.repository.ReportedListingRepository;
import com.example.secondtreasurebe.repository.UnspecifiedTopUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReportedListingService {
    @Autowired
    private ReportedListingRepository reportedListingRepository;

    public ResponseEntity<Object> getAllReportedListing() {
        return new ResponseEntity<>(reportedListingRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> remove(int id) {
        if (reportedListingRepository.existsById(id)){
            reportedListingRepository.deleteById(id);
            return new ResponseEntity<>("The listing has been removed", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("ERROR: Cannot remove the listing", HttpStatus.NOT_FOUND);
        }
    }
}
