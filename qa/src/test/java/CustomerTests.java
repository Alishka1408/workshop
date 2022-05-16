import core.db.tables.customer.CustomerEntity;
import model.Customer;
import model.CustomerResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import service.CustomerService;

import java.util.List;
import java.util.Objects;

import static core.db.queries.customer.CustomerQueries.getCustomerById;
import static core.db.queries.customer.CustomerQueries.getCustomers;
import static core.utils.TestDataGenerators.getRandomDigits;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTests {
    private String serNo = null;
    private String number = null;
    private Customer customer = null;
    private CustomerEntity entity = null;
    private List<CustomerEntity> entityList = null;
    private CustomerService customerService = null;
    private CustomerResponse customerBeforeEach = null;
    private ResponseEntity<CustomerResponse> response = null;
    private ResponseEntity<List<CustomerResponse>> responseList = null;

    @BeforeEach
    public void init() {
        String serNo = "1234567";
        customer = Customer.builder()
                .firstName("Alexander")
                .lastName("Petrov")
                .email("alex@gmail.com")
                .serNo(serNo)
                .build();

        customerService = new CustomerService();
        response = customerService.createCustomer(customer);
        customerBeforeEach = response.getBody();
    }

    @AfterEach
    public void clear() {
        customerService.deleteCustomer(customerBeforeEach.getId());
    }

    @Test
    @DisplayName("Create a customer")
    public void createCustomerTest() {
        step("Prerequisites", () -> {
            customerService = new CustomerService();
            customer = Customer.builder()
                    .firstName("Alexander")
                    .lastName("Petrov")
                    .email("alex@gmail.com")
                    .serNo("1234567")
                    .build();
        });
        step("Create a customer", () -> {
            response = customerService.createCustomer(customer);
        });
        step("Get customer from DB by ID", () -> {
            entityList = getCustomerById(Objects.requireNonNull(response.getBody()).getId());
        });
        step("Verify the response", () -> {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertThat(entityList.get(0)).usingRecursiveComparison().isEqualTo(
                    Objects.requireNonNull(response.getBody()));
        });
    }

    @Test
    @DisplayName("Get all customers")
    public void getAllCustomersTest() {
        step("Get the customers directly from DB", () -> {
            entityList = getCustomers();
        });
        step("Get the customers from service", () -> {
            customerService = new CustomerService();
            responseList = customerService.getAllCustomers();
        });
        step("Verify the response", () -> {
            assertEquals(HttpStatus.OK, responseList.getStatusCode());
            assertThat(entityList).usingRecursiveComparison().isEqualTo(
                    Objects.requireNonNull(responseList.getBody()));
        });
    }

    @Test
    @DisplayName("Get a customer")
    public void getCustomerTest() {
        step("Get the customer directly from DB", () -> {
            entity = getCustomers().get(0);
        });
        step("Get the customers from service", () -> {
            customerService = new CustomerService();
            response = customerService.getCustomer(entity.getId());
        });
        step("Verify the response", () -> {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertThat(entity).usingRecursiveComparison().isEqualTo(
                    Objects.requireNonNull(response.getBody()));
        });
    }

    @Test
    @DisplayName("Delete a customer")
    public void deleteCustomerTest() {
        step("Get the customer directly from DB", () -> {
            entity = getCustomers().get(0);
        });
        step("Delete the customer from service", () -> {
            customerService = new CustomerService();
            customerService.deleteCustomer(entity.getId());
        });
        step("SQL query to DB to verify that the customer was deleted", () -> {
            assertThrows(IndexOutOfBoundsException.class,
                    () -> getCustomerById(entity.getId()).get(0));
        });
    }

    @Test
    @DisplayName("Update a customer")
    public void updateCustomerTest() {
        step("Prerequisites", () -> {
            number = getRandomDigits(111);
        });
        step("Get the customer directly from DB", () -> {
            entity = getCustomers().get(0);
            serNo = entity.getSerNo();
            assertNotEquals(number, serNo);
        });
        step("Get the customer from service", () -> {
            customerService = new CustomerService();
            entity.setSerNo(number);
            response = customerService.updateCustomer(entity);
        });
        step("Verify the response", () -> {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotEquals(serNo, Objects.requireNonNull(response.getBody()).getSerNo());
        });
    }
}