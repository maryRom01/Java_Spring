package app.controllers;

import app.entities.Customer;
import app.services.serviceInterface.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Log4j2
public class CustomerController {

    private final CustomerService customerService;

    private final static ResponseEntity<Customer> emptyCustomer =
            ResponseEntity.notFound().build();

    private final static ResponseEntity<Boolean> falseFlag =
            ResponseEntity.notFound().build();

    private final static ResponseEntity<List<Customer>> emptyCustomerList =
            ResponseEntity.notFound().build();

    // GET http://localhost:8081/customers
    @GetMapping("customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        Optional<List<Customer>> allCustomers = customerService.findAll();
        return allCustomers
                .map(c -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(c))
                .orElse(emptyCustomerList);
    }

    // GET http://localhost:8081/customerByName?name=Customer%20B
    @GetMapping("customerByName")
    public ResponseEntity<List<Customer>> getCustomerByName(@RequestParam String name) {
        Optional<List<Customer>> customersByNameList = customerService.findByName(name);
        return customersByNameList
                .map(c -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(c))
                .orElse(emptyCustomerList);
    }

    // GET http://localhost:8081/customerByEmail?email=a@aol.com
    @GetMapping("customerByEmail")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String email) {
        Optional<Customer> customerByEmail = customerService.findByEmail(email);
        return customerByEmail
                .map(c -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(c))
                .orElse(emptyCustomer);
    }

    // GET http://localhost:8081/customerById?id=3
    @GetMapping("customerById")
    public ResponseEntity<Customer> getCustomerById(@RequestParam long id) {
        Optional<Customer> customerById = customerService.getOne(id);
        return customerById
                .map(c -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(c))
                .orElse(emptyCustomer);
    }

    // DELETE http://localhost:8081/deleteCustomerById/3
    @DeleteMapping("deleteCustomerById/{id}")
    public ResponseEntity<Boolean> deleteCustomerById(@PathVariable("id") long id) {
        Optional<Boolean> flag = customerService.deleteById(id);
        return flag
                .map(f -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(f))
                .orElse(falseFlag);
    }

    // POST http://localhost:8081/customer
    @PostMapping("customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Optional<Customer> customerById = customerService.save(customer);
        return customerById
                .map(c -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(c))
                .orElse(emptyCustomer);
    }

    // POST http://localhost:8081/customerAll
    @PostMapping("customersAll")
    public ResponseEntity<List<Customer>> saveAllCustomers(@RequestBody List<Customer> customers) {
        Optional<List<Customer>> customersResult = customerService.saveAll(customers);
        return customersResult
                .map(c -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(c))
                .orElse(emptyCustomerList);
    }

}
