package com.example.secondtreasurebe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListingDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public ListingDTO(){

    }
}