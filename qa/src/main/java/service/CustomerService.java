package service;

import core.CustomErrorHandler;
import core.db.tables.customer.CustomerEntity;
import model.Customer;
import model.CustomerResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class CustomerService {
    public String url = "http://localhost:8080/customers/";

    RestTemplate restTemplate = new RestTemplate() {{
        setErrorHandler(new CustomErrorHandler());
    }};

    public ResponseEntity<CustomerResponse> createCustomer(Customer customer) {
        HttpEntity<?> requestEntity = new HttpEntity<>(customer, null);
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
    }

    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
    }

    public ResponseEntity<CustomerResponse> getCustomer(int id) {
        return restTemplate.exchange(
                url + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
    }

    public ResponseEntity<CustomerResponse> updateCustomer(CustomerEntity customer) {
        HttpEntity<?> requestEntity = new HttpEntity<>(customer, null);
        return restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
    }

    public void deleteCustomer(int id) {
        restTemplate.delete(url + id);
    }
}