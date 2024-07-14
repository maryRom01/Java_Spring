package app.controllers;

import app.entities.Account;
import app.entities.Customer;
import app.services.serviceInterface.AccountService;
import app.services.serviceInterface.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    private final static ResponseEntity<List<Account>> emptyAccountList =
            ResponseEntity.notFound().build();

    //http://localhost:9000/customer/3/accounts
    @GetMapping("customer/{id}/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(@PathVariable("id") long id) {
        Optional<Customer> customer = customerService.getOne(id);
        List<Account> accounts = accountService.getAccountByCustomer(customer.get());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accounts);
    }
}
