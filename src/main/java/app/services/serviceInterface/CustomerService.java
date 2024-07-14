package app.services.serviceInterface;

import app.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> save(Customer customer);
    boolean delete(Customer customer);
    void deleteAll(List<Customer> customers);
    Optional<List<Customer>> saveAll(List<Customer> customers);
    Optional<List<Customer>> findAll();
    Optional<Boolean> deleteById(long id);
    Optional<Customer> getOne(long id);
    Optional<List<Customer>> findByName(String name);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findById(long id);
}
