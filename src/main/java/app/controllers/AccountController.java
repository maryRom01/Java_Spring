package app.controllers;

import app.entities.Account;
import app.entities.Customer;
import app.services.serviceInterface.AccountService;
import app.services.serviceInterface.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("api/v1/")
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
    //http://localhost:9000/api/v1/customer/3/accounts
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
    //http://localhost:9000/api/v1/accountI/increase/90810e49-5ad6-40e3-9d02-ee9a42d7dd67/sum/1500.5
    @PostMapping("account/increase/{number}/amount/{amount}")
    public ResponseEntity<Account> increaseAccount(@PathVariable("number") String number,
                                                   @PathVariable("amount") double amount) {
        Optional<Account> account = accountService.increaseAccountSum(number, amount);
        return account.map(
                        a -> ResponseEntity
                                .status(HttpStatus.OK)
                                .body(a))
                .orElse(emptyAccount);
    }

    /**
     * Зняти гроші з рахунку
     * */
    //http://localhost:9000/api/v1/accountD/decrease/90810e49-5ad6-40e3-9d02-ee9a42d7dd67/sum/150.5
    @PostMapping("account/decrease/{number}/amount/{amount}")
    public ResponseEntity<Account> decreaseAccount(@PathVariable("number") String number,
                                                   @PathVariable("amount") double amount) {
        Optional<Account> account = accountService.decreaseAccountSum(number, amount);
        return account.map(
                        a -> ResponseEntity
                                .status(HttpStatus.OK)
                                .body(a))
                .orElse(emptyAccount);
    }

    /**
     * Переказати гроші на інший рахунок
     * */
    @PostMapping("transfer/{from}/{to}/amount/{amount}")
    public ResponseEntity<Account> transfer(@PathVariable("from") String accountFrom,
                                            @PathVariable("to") String accountTo,
                                            @PathVariable("amount") double amount) {
        Optional<Account> accountF = accountService.decreaseAccountSum(accountFrom, amount);
        if (accountF.isPresent()) {
            Optional<Account> accountT = accountService.increaseAccountSum(accountTo, amount);
            return accountT.map(
                            a -> ResponseEntity
                                    .status(HttpStatus.OK)
                                    .body(a))
                    .orElse(emptyAccount);
        }
        return emptyAccount;
    }
}
