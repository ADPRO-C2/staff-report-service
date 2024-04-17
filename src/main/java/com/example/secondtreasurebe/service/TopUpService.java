package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.TopUp;
import com.example.secondtreasurebe.repository.TopUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopUpService {
    @Autowired
    private TopUpRepository topUpRepository;

    public List<TopUp> getAll() {
        return topUpRepository.findAll();
    }
}
