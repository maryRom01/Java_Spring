package app.dao.daoInterface;

import app.entities.Account;
import app.entities.Customer;

public interface AccountDAO extends DAO<Account> {

    public Account getAccountByEmail(String email);
    public Account getAccountByCustomer(Customer customer);
    public Account findByNumber(String number);
}
