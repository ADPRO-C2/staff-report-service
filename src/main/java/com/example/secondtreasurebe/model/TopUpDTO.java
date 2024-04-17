package com.example.secondtreasurebe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopUpDTO {
    private int balance;
    private int cost;

    public TopUpDTO(){

    }

    public TopUpDTO(int balance, int cost){
        this.balance = balance;
        this.cost = cost;
    }
}
