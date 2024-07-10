package app.services.serviceInterface;

import app.entities.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    boolean delete(Customer customer);
    void deleteAll(List<Customer> customers);
    void saveAll(List<Customer> customers);
    List<Customer> findAll();
    boolean deleteById(long id);
    Customer getOne(long id);
    public List<Customer> findByName(String name);
    public Customer findByEmail(String email);
}
