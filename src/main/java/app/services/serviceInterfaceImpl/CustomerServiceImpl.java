package app.services.serviceInterfaceImpl;

import app.dao.daoInterfaceImpl.CustomerDAOImpl;
import app.entities.Customer;
import app.services.serviceInterface.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAOImpl customerDAO;

    public CustomerServiceImpl(CustomerDAOImpl customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public Optional<Customer> save(Customer customer) {
        if (customerDAO.findAll().isPresent()) {
            customerDAO.save(customer);
            return Optional.of(customer);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Customer customer) {
        return false;
    }

    @Override
    public void deleteAll(List<Customer> customers) {

    }

    @Override
    public Optional<List<Customer>> saveAll(List<Customer> customers) {
        return customerDAO.saveAll(customers);
    }

    @Override
    public Optional<List<Customer>> findAll() {
        if (customerDAO.findAll().isEmpty()) return Optional.empty();
        return customerDAO.findAll();
    }

    @Override
    public Optional<Boolean> deleteById(long id) {
        return customerDAO.deleteById(id);
    }

    @Override
    public Optional<Customer> getOne(long id) {
        if (customerDAO.getOne(id).isEmpty()) return Optional.empty();
        return customerDAO.getOne(id);
    }

    @Override
    public Optional<List<Customer>> findByName(String name) {
        if (customerDAO.findByName(name).isEmpty()) return Optional.empty();
        return customerDAO.findByName(name);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerDAO.findByEmail(email);
    }
}
