package app.controllers;

import app.entities.Customer;
import app.services.serviceInterface.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class CustomerController {

    private final CustomerService customerService;

    //http://localhost:8081/customers
    @GetMapping("customers")
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
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

    //http://localhost:8081/customerById?id=3
    @GetMapping("customerById")
    public Customer getCustomerById(@RequestParam long id) {
        return customerService.getOne(id);
    }

    @DeleteMapping("deleteCustomerById/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable("id") long id) {
        boolean flag = customerService.deleteById(id);
        if (flag) return ResponseEntity.ok("Deleted Customer with id " + id);
        else return ResponseEntity.notFound().build();
    }
}
