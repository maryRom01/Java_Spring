package app.services.serviceInterfaceImpl;

import app.dao.daoInterfaceImpl.AccountDAOImpl;
import app.dao.daoInterfaceImpl.CustomerDAOImpl;
import app.entities.Account;
import app.entities.Customer;
import app.entities.enums.Currency;
import app.services.serviceInterface.AccountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDAOImpl accountDAO;
    private final CustomerDAOImpl customerDAO;

    public AccountServiceImpl(AccountDAOImpl accountDAO, CustomerDAOImpl customerDAO) {
        this.accountDAO = accountDAO;
        this.customerDAO = customerDAO;
    }

    public Optional<Account> getAccountIfExists(Account account) {
        Optional<Customer> customer = customerDAO.findById(account.getCustomerId());
        if (customer.isPresent()) {
             Account foundAccount = customer.get()
                    .getAccounts()
                    .stream()
                    .filter(a -> a.getNumber().equals(account.getNumber()))
                    .findFirst()
                    .orElse(null);
             if (foundAccount != null) return Optional.of(foundAccount);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> save(Account account) {
        getAccountIfExists(account).ifPresent(value -> {
            value.setBalance(account.getBalance());
        });
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> delete(Account account) {
        Customer customer = customerDAO.findById(account.getCustomerId()).orElse(null);
        if (customer != null) {
            Optional<Account> accountOptional = getAccountIfExists(account);
            if (accountOptional.isPresent()) {
                customer.getAccounts().remove(accountOptional.get());
                return Optional.of(true);
            }
        }
        return Optional.of(false);
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
    public Optional<List<Account>> getAccountByCustomer(Customer customer) {
        Optional<List<Account>> accounts = accountDAO.getAccountByCustomer(customer);
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

    @Override
    public Optional<Account> increaseAccountSum(String number, double sum) {
        Optional<List<Customer>> customers = customerDAO.findAll();
        if (customers.isPresent()) {
            List<Customer> customerList = customers.get();
            List<Account> accounts = new ArrayList<>();
            customerList.forEach(customer -> {
                List<Account> customerAccounts = customer.getAccounts();
                customerAccounts.forEach(customerAccount -> {
                    accounts.add(customerAccount);
                });
            });
            if (accounts.size() > 0) {
                 Account requiredAccount = accounts
                         .stream()
                         .filter(account -> account.getNumber().equals(number))
                         .collect(Collectors.toList()).get(0);
                 if (requiredAccount != null) {
                     requiredAccount.setBalance(requiredAccount.getBalance() + sum);
                     return Optional.of(requiredAccount);
                 }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> decreaseAccountSum(String number, double sum) {
        Optional<List<Customer>> customers = customerDAO.findAll();
        if (customers.isPresent()) {
            List<Customer> customerList = customers.get();
            List<Account> accounts = new ArrayList<>();
            customerList.forEach(customer -> {
                List<Account> customerAccounts = customer.getAccounts();
                customerAccounts.forEach(customerAccount -> {
                    accounts.add(customerAccount);
                });
            });
            if (accounts.size() > 0) {
                Account requiredAccount = accounts
                        .stream()
                        .filter(account -> account.getNumber().equals(number))
                        .collect(Collectors.toList()).get(0);
                if (requiredAccount != null) {
                    if (requiredAccount.getBalance() < sum) {
                        return Optional.empty();
                    } else {
                        requiredAccount.setBalance(requiredAccount.getBalance() - sum);
                        return Optional.of(requiredAccount);
                    }
                }
            }
        }
        return Optional.empty();
    }
}
