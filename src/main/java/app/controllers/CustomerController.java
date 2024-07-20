package app.controllers;

import app.entities.Account;
import app.entities.Customer;
import app.entities.enums.Currency;
import app.services.serviceInterface.AccountService;
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
@RequestMapping("api/v1")
public class CustomerController {

    private final CustomerService customerService;
    private final AccountService accountService;

    private final static ResponseEntity<Customer> emptyCustomer =
            ResponseEntity.notFound().build();

    private final static ResponseEntity<Boolean> falseFlag =
            ResponseEntity.notFound().build();

    private final static ResponseEntity<List<Customer>> emptyCustomerList =
            ResponseEntity.notFound().build();

    private final static ResponseEntity<Account> emptyAccount =
            ResponseEntity.notFound().build();

    /**
     * Отримати інформацію про всіх користувачів, включаючи їх рахунки
     * */
    // GET http://localhost:9000/api/v1/customers
    @GetMapping("customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        Optional<List<Customer>> allCustomers = customerService.findAll();
        return allCustomers
                .map(c -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(c))
                .orElse(emptyCustomerList);
    }

    /**
     * Отримати інформацію про окремого користувача, включаючи його рахунки
     * */
    // GET http://localhost:9000/api/v1/customer/id?id=3
    @GetMapping("customer/id")
    public ResponseEntity<Customer> getCustomerById(@RequestParam long id) {
        Optional<Customer> customerById = customerService.getOne(id);
        return customerById
                .map(c -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(c))
                .orElse(emptyCustomer);
    }

    // GET http://localhost:9000/api/v1/customer/name?name=Customer%20B
    @GetMapping("customer/name")
    public ResponseEntity<List<Customer>> getCustomerByName(@RequestParam String name) {
        Optional<List<Customer>> customersByNameList = customerService.findByName(name);
        return customersByNameList
                .map(c -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(c))
                .orElse(emptyCustomerList);
    }

    // GET http://localhost:9000/api/v1/customer/email?email=a@aol.com
    @GetMapping("customer/email")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String email) {
        Optional<Customer> customerByEmail = customerService.findByEmail(email);
        return customerByEmail
                .map(c -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(c))
                .orElse(emptyCustomer);
    }

    /**
     * Видалити користувача
     * */
    // DELETE http://localhost:9000/api/v1/customer/3
    @DeleteMapping("customer/{id}")
    public ResponseEntity<Boolean> deleteCustomerById(@PathVariable("id") long id) {
        Optional<Boolean> flag = customerService.deleteById(id);
        return flag
                .map(f -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(f))
                .orElse(falseFlag);
    }

    /**
     * Видалити рахунок у користувача
     */
    // DELETE http://localhost:9000/api/v1/customer/{id}/account/{accId}
    @DeleteMapping("customer/{id}/account/{accId}")
    public ResponseEntity<Boolean> deleteCustomerAccount(@PathVariable("id") long id,
                                                                    @PathVariable("accId") long accId) {
        Customer customerById = customerService.getOne(id).get();
        Optional<Boolean> flag = Optional.of(false);
        Optional<List<Account>> accounts = accountService.getAccountByCustomer(customerById);
        if (accounts.isPresent()) {
            Account requiredAccount = accounts.get()
                    .stream()
                    .filter(a -> a.getId() == accId).findFirst()
                    .orElse(null);
            if (requiredAccount != null) {
                flag = accountService.delete(requiredAccount);
            }
        }
        return flag
                .map(f -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(f))
                .orElse(falseFlag);
    }

    /**
     * Cтворити користувача
     * Змінити користувача
     * */
    // POST http://localhost:9000/api/v1/customer
    @PostMapping("customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Optional<Customer> savedCustomer = customerService.save(customer);
        return savedCustomer
                .map(c -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(c))
                .orElse(emptyCustomer);
    }

    /**
     * Створити рахунок для конкретного користувача
     * */
    @PostMapping("customer/{id}/account/{curr}")
    public ResponseEntity<Account> createAccount(@PathVariable("id") long id, @PathVariable("curr") Currency curr) {
        Optional<Customer> customer = customerService.getOne(id);
        if (customer.isPresent()) {
            Account account = accountService.createAccount(curr, customer.get());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(account);
        }
        return emptyAccount;
    }

    // POST http://localhost:9000/api/v1/customers
    @PostMapping("customers")
    public ResponseEntity<List<Customer>> saveAllCustomers(@RequestBody List<Customer> customers) {
        Optional<List<Customer>> customersResult = customerService.saveAll(customers);
        return customersResult
                .map(c -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(c))
                .orElse(emptyCustomerList);
    }

}
