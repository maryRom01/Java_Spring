package app.services.serviceInterfaceImpl;

import app.dao.daoInterfaceImpl.CustomerDAOImpl;
import app.entities.Customer;
import app.services.serviceInterface.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAOImpl customerDAO;

    public CustomerServiceImpl(CustomerDAOImpl customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public boolean delete(Customer customer) {
        return false;
    }

    @Override
    public void deleteAll(List<Customer> customers) {

    }

    @Override
    public void saveAll(List<Customer> customers) {

    }

    @Override
    public List<Customer> findAll() {
        return List.of();
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public Customer getOne(long id) {
        return null;
    }

    @Override
    public List<Customer> findByName(String name) {
        return new ArrayList<>(customerDAO.findByName(name));
    }

    @Override
    public Customer findByEmail(String email) {
        return customerDAO.findByEmail(email);
    }
}
