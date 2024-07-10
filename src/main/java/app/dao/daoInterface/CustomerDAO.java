package app.dao.daoInterface;

import app.entities.Customer;

import java.util.List;

public interface CustomerDAO extends DAO<Customer> {

    public List<Customer> findByName(String name);
    public Customer findByEmail(String email);
}
