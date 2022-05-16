package com.workshop.customer.controller;

import com.workshop.customer.dao.Customer;
import com.workshop.customer.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer registerCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Integer id) {
        return customerService.findById(id);
    }

    @PutMapping()
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteCustomerById(@PathVariable Integer id) {
        return customerService.deleteById(id);
    }
}