package app.dao.daoInterface;

import app.entities.Account;
import app.entities.Customer;

public interface AccountDAO extends DAO<Account> {

    Account getAccountByEmail(String email);
    Account getAccountByCustomer(Customer customer);
    Account findByNumber(String number);
}
