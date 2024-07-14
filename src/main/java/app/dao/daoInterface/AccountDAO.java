package app.dao.daoInterface;

import app.entities.Account;
import app.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface AccountDAO extends DAO<Account> {

    Optional<List<Account>> getAccountByEmail(String email);
    List<Account> getAccountByCustomer(Customer customer);
    Optional<Account> findByNumber(String number);
}
