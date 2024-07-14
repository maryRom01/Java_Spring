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
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    private final static ResponseEntity<Account> emptyAccount =
            ResponseEntity.notFound().build();

    private final static ResponseEntity<List<Account>> emptyAccountList =
            ResponseEntity.notFound().build();

    /**
     * Отримати всі рахунки для користувача
     * */
    //http://localhost:9000/customer/3/accounts
    @GetMapping("customer/{id}/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(@PathVariable("id") long id) {
        Optional<Customer> customer = customerService.getOne(id);
        Optional<List<Account>> accounts = accountService.getAccountByCustomer(customer.get());
        return accounts.map(
                a -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(a))
                .orElse(emptyAccountList);
    }

    /**
     * Поповнити рахунок
     * */
    //http://localhost:9000/accountI/90810e49-5ad6-40e3-9d02-ee9a42d7dd67/sum/1500.5
    @PostMapping("accountI/{number}/sum/{sum}")
    public ResponseEntity<Account> increaseAccount(@PathVariable("number") String number,
                                                   @PathVariable("sum") double sum) {
        Optional<Account> account = accountService.increaseAccountSum(number, sum);
        return account.map(
                        a -> ResponseEntity
                                .status(HttpStatus.OK)
                                .body(a))
                .orElse(emptyAccount);
    }

    /**
     * Зняти гроші з рахунку
     * */
    //http://localhost:9000/accountD/90810e49-5ad6-40e3-9d02-ee9a42d7dd67/sum/150.5
    @PostMapping("accountD/{number}/sum/{sum}")
    public ResponseEntity<Account> decreaseAccount(@PathVariable("number") String number,
                                                   @PathVariable("sum") double sum) {
        Optional<Account> account = accountService.decreaseAccountSum(number, sum);
        return account.map(
                        a -> ResponseEntity
                                .status(HttpStatus.OK)
                                .body(a))
                .orElse(emptyAccount);
    }
}
