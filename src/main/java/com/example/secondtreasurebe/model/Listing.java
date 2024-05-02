package com.example.secondtreasurebe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Listing(){

    }
}