package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.Listing;
import com.example.secondtreasurebe.repository.ReportedListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class ReportedListingService {
    @Autowired
    private ReportedListingRepository reportedListingRepository;

    public ResponseEntity<List<Listing>> getAllReportedListing() {
        return new ResponseEntity<>(reportedListingRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> addReportedListing(String id) {
        String uri = "http://34.142.129.98/api/listing/" + id;
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        Listing listing;
        try {
            listing = restTemplate.getForObject(uri, Listing.class);
        }
        catch (Exception e) {
            return new ResponseEntity<>("ERROR: Cannot add the listing", HttpStatus.NOT_FOUND);
        }

        reportedListingRepository.save(listing);
        return new ResponseEntity<>("The listing has been added", HttpStatus.OK);
    }

    public ResponseEntity<String> ignoreReportedListing(String id) {
        String uri= String.format("http://34.142.129.98/api/listing/{}", id);
        RestTemplate restTemplate = new RestTemplate();
        Listing listing;
        try {
            listing = restTemplate.getForObject(uri, Listing.class);
        }
        catch (Exception e) {
            return new ResponseEntity<>("ERROR: Cannot remove the listing", HttpStatus.NOT_FOUND);
        }

        reportedListingRepository.deleteById(id);
        return new ResponseEntity<>("The listing has been ignored", HttpStatus.OK);
    }

    public ResponseEntity<String> removeListing(String id) throws IOException {
        String uri= String.format("http://34.142.129.98/api/listing/{}", id);
        RestTemplate restTemplate = new RestTemplate();
        Listing listing;
        try {
            listing = restTemplate.getForObject(uri, Listing.class);
        }
        catch (Exception e) {
            return new ResponseEntity<>("ERROR: Cannot remove the listing", HttpStatus.NOT_FOUND);
        }

        reportedListingRepository.deleteById(id);
        URL url = new URL(String.format("http://34.142.129.98/api/delete-listing/{}", id));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        return new ResponseEntity<>("The listing has been removed", HttpStatus.OK);
    }
}
