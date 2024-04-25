package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.TopUp;
import com.example.secondtreasurebe.repository.AcceptedTopUpRepository;
import com.example.secondtreasurebe.repository.RejectedTopUpRepository;
import com.example.secondtreasurebe.repository.UnspecifiedTopUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopUpService {
    @Autowired
    private UnspecifiedTopUpRepository unspecifiedTopUpRepository;
    @Autowired
    private AcceptedTopUpRepository acceptedTopUpRepository;
    @Autowired
    private RejectedTopUpRepository rejectedTopUpRepository;

    public ResponseEntity<Object> getAllUnspecifiedTopUps() {
        return new ResponseEntity<>(unspecifiedTopUpRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllAcceptedTopUps() {
        return new ResponseEntity<>(acceptedTopUpRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllrejectedTopUps() {
        return new ResponseEntity<>(rejectedTopUpRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> accept(int id) {
        if (unspecifiedTopUpRepository.existsById(id)){
            TopUp topUp = unspecifiedTopUpRepository.findById(id).get();
            unspecifiedTopUpRepository.deleteById(topUp.getId());
            acceptedTopUpRepository.save(topUp);
            return new ResponseEntity<>("TopUp has been accepted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("ERROR: Cannot accept TopUp", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> reject(int id) {
        if (unspecifiedTopUpRepository.existsById(id)){
            TopUp topUp = unspecifiedTopUpRepository.findById(id).get();
            unspecifiedTopUpRepository.deleteById(topUp.getId());
            rejectedTopUpRepository.save(topUp);
            return new ResponseEntity<>("TopUp has been rejected", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("ERROR: Cannot reject TopUp", HttpStatus.NOT_FOUND);
        }
    }
}
