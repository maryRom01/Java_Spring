package app.dao.daoInterface;

import app.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO extends DAO<Customer> {

    Optional<List<Customer>> findByName(String name);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findById(long id);
}
