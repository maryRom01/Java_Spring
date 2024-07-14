package app.services.serviceInterface;

import app.entities.Account;
import app.entities.Customer;
import app.entities.enums.Currency;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> save(Account obj);
    boolean delete(Account obj);
    void deleteAll(List<Account> entities);
    Optional<List<Account>> saveAll(List<Account> entities);
    Optional<List<Account>> findAll();
    Optional<Boolean> deleteById(long id);
    Optional<Account> getOne(long id);
    Optional<Account> getAccountByEmail(String email);
    List<Account> getAccountByCustomer(Customer customer);
    Optional<Account> findByNumber(String number);
    Account createAccount(Currency currency, Customer customer);
}
