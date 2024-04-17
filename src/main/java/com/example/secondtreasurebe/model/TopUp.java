package com.example.secondtreasurebe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TopUp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int balance;
    private int cost;

    public TopUp(){

    }
}
