package app.services.serviceInterfaceImpl;

import app.dao.daoInterfaceImpl.AccountDAOImpl;
import app.dao.daoInterfaceImpl.CustomerDAOImpl;
import app.entities.Account;
import app.entities.Customer;
import app.entities.enums.Currency;
import app.services.serviceInterface.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDAOImpl accountDAO;

    public AccountServiceImpl(AccountDAOImpl accountDAO) {
        this.accountDAO = accountDAO;
    }


    @Override
    public Optional<Account> save(Account obj) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Account obj) {
        return false;
    }

    @Override
    public void deleteAll(List<Account> entities) {

    }

    @Override
    public Optional<List<Account>> saveAll(List<Account> entities) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Account>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> deleteById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Account> getOne(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Account> getAccountByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<Account> getAccountByCustomer(Customer customer) {
        List<Account> accounts = accountDAO.getAccountByCustomer(customer);
        return accounts;
    }

    @Override
    public Optional<Account> findByNumber(String number) {
        return Optional.empty();
    }

    @Override
    public Account createAccount(Currency currency, Customer customer) {
        Account account = accountDAO.createAccount(currency, customer);
        return account;
    }
}
