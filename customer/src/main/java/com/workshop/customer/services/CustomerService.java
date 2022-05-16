package com.workshop.customer.services;

import com.workshop.customer.dao.Customer;
import com.workshop.customer.dao.CustomerRepository;
import com.workshop.customer.model.FraudResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public Customer saveCustomer(Customer request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .serNo(request.getSerNo())
                .build();

        customer = customerRepository.saveAndFlush(customer);
        log.info("New customer registration {}", customer);
        isFraudulent(customer);
        return customer;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(int id) {
        return customerRepository.findById(id);
    }

    public Customer update(Customer request) {
        Customer customer = Customer.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .serNo(request.getSerNo())
                .build();

        customer = customerRepository.saveAndFlush(customer);
        log.info("The customer was updated {}", customer);
        isFraudulent(customer);
        return customer;
    }

    public String deleteById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.deleteById(id);
            restTemplate.delete("http://localhost:8082/fraud/" + id);
            return "The customer: " + id + " was deleted";
        } else {
            return "The customer is not found for the id " + id;
        }
    }

    public void isFraudulent(Customer customer) {
        FraudResponse fraudResponse = restTemplate.getForObject(
                "http://localhost:8082/fraud?customerId={customerId}&serNo={serNo}",
                FraudResponse.class,
                customer.getId(),
                customer.getSerNo()
        );

        assertNotNull(fraudResponse);
        if (fraudResponse.isFraudster()) {
            log.info("This customer is fraudulent {}", customer);
        }
    }
}