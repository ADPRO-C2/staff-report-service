package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.Listing;
import com.example.secondtreasurebe.model.TopUpStatus;
import com.example.secondtreasurebe.model.TopUpTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TopUpService {

    public ResponseEntity<List<TopUpTransaction>> getAllTopUps() {
        String uri= "http://34.143.169.241/topups/";
        RestTemplate restTemplate = new RestTemplate();
        List<TopUpTransaction> topups;
        try {
            topups = restTemplate.getForObject(uri, List.class);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return new ResponseEntity<>(topups, HttpStatus.OK);
    }

    public ResponseEntity<String> approve(String string_id) throws IOException {
        String uri= String.format("http://34.143.169.241/topups/{}", string_id);
        RestTemplate restTemplate = new RestTemplate();
        TopUpTransaction topUpTransaction;
        try {
            topUpTransaction = restTemplate.getForObject(uri, TopUpTransaction.class);
        }
        catch (Exception e) {
            return new ResponseEntity<>("ERROR: Cannot accept TopUp", HttpStatus.NOT_FOUND);
        }

        UUID id = UUID.fromString(string_id);
        topUpTransaction.setStatus(TopUpStatus.APPROVED);
        uri = "http://34.143.169.241/topups/";
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String topupAsJson = new ObjectMapper().writeValueAsString(topUpTransaction);
        HttpEntity<String> request = new HttpEntity<>(topupAsJson, headers);
        restTemplate.postForObject(uri, request, String.class);
        return new ResponseEntity<>("TopUp has been accepted", HttpStatus.OK);
    }

    public ResponseEntity<String> reject(String string_id) throws JsonProcessingException {
        String uri= String.format("http://34.143.169.241/topups/{}", string_id);
        RestTemplate restTemplate = new RestTemplate();
        TopUpTransaction topUpTransaction;
        try {
            topUpTransaction = restTemplate.getForObject(uri, TopUpTransaction.class);
        }
        catch (Exception e) {
            return new ResponseEntity<>("ERROR: Cannot reject TopUp", HttpStatus.NOT_FOUND);
        }

        topUpTransaction.setStatus(TopUpStatus.REJECTED);
        uri = "http://34.143.169.241/topups/";
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String topupAsJson = new ObjectMapper().writeValueAsString(topUpTransaction);
        HttpEntity<String> request = new HttpEntity<>(topupAsJson, headers);
        restTemplate.postForObject(uri, request, String.class);
        return new ResponseEntity<>("TopUp has been accepted", HttpStatus.OK);
    }
}
