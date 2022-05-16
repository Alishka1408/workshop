package com.workshop.fraud.controller;

import com.workshop.fraud.model.FraudResponse;
import com.workshop.fraud.services.FraudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fraud")
@AllArgsConstructor
@Slf4j
public class FraudController {

    private final FraudService fraudService;

    @GetMapping()
    public FraudResponse isFraudster(
            @RequestParam(required = false) Integer customerId,
            @RequestParam(required = false) String serNo) {
        boolean isFraudulentCustomer = fraudService.
                isFraudulentCustomer(customerId, serNo);
        log.info("Fraud check request for customer {}", customerId);
        return new FraudResponse(isFraudulentCustomer);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteCustomerById(@PathVariable Integer id) {
        return fraudService.deleteById(id);
    }
}