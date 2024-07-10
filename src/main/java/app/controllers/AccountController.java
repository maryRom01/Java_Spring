package app.controllers;

import app.entities.Account;
import app.entities.Customer;
import app.entities.enums.Currency;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AccountController {

    private final List<Account> accounts = List.of(
            new Account(Currency.USD, new Customer(1, "A", "a@aol.com", 25, new ArrayList<>())),
            new Account(Currency.USD, new Customer(1, "A", "a@aol.com", 25, new ArrayList<>())),
            new Account(Currency.USD, new Customer(1, "A", "a@aol.com", 25, new ArrayList<>()))
    );

    @GetMapping("accounts")
    public List<Account> allAccounts() {
        return accounts;
    }
}
