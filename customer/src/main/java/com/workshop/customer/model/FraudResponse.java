package com.workshop.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FraudResponse {
    private Boolean isFraudster;

    public boolean isFraudster() {
        return isFraudster;
    }
}
