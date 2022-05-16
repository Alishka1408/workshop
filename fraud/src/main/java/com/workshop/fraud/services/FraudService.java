package com.workshop.fraud.services;

import com.workshop.fraud.dao.Fraud;
import com.workshop.fraud.dao.FraudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class FraudService {
    private final FraudRepository fraudRepository;

    public boolean isFraudulentCustomer(int customerId, String serNo) {
        boolean var = isPassportNumberValid.test(serNo);
        int id = getId(customerId);
        fraudRepository.save(
                Fraud.builder()
                        .id(id)
                        .customerId(customerId)
                        .isFraudster(var)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return var;
    }

    public String deleteById(int customerId) {
        int id = getId(customerId);
        if (id > 0) {
            fraudRepository.deleteById(id);
            return "The customer: " + id + " was deleted";
        } else {
            return "The customer is not found for the id " + id;
        }
    }

    public int getId(int customerId) {
        if (fraudRepository.existsByCustomerId(customerId)) {
            return fraudRepository.findByCustomerId(customerId).getId();
        } else {
            return 0;
        }
    }

    static Predicate<String> isPassportNumberValid = x -> !x.startsWith("1234") || !(x.length() == 7);
}