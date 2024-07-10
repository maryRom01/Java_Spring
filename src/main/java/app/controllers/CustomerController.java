package app.controllers;

import app.entities.Account;
import app.entities.Customer;
import app.services.serviceInterface.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("customers")
    public List<Customer> getAllCustomers() {
        return null;
    }

    //http://localhost:8081/customerByName?name=Customer%20B
    @GetMapping("customerByName")
    public List<Customer> getCustomerByName(@RequestParam String name) {
        return customerService.findByName(name);
    }

    //http://localhost:8081/customerByEmail?email=a@aol.com
    @GetMapping("customerByEmail")
    public Customer getCustomerByEmail(@RequestParam String email) {
        return customerService.findByEmail(email);
    }
}
