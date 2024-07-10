package app.dao.daoInterfaceImpl;

import app.dao.daoInterface.CustomerDAO;
import app.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CustomerDAOImpl implements CustomerDAO {

    private final List<Customer> customers = List.of(
            new Customer(1, "Customer A", "a@aol.com", 45, new ArrayList<>()),
            new Customer(2, "Customer B", "b@aol.com", 31, new ArrayList<>()),
            new Customer(3, "Customer B", "b2@aol.com", 38, new ArrayList<>()),
            new Customer(4, "Customer C", "c@aol.com", 77, new ArrayList<>())
    );

    @Override
    public List<Customer> findByName(String name) {
        return customers.stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public Customer findByEmail(String email) {
        return customers.stream().filter(c -> c.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public Customer save(Customer obj) {
        return null;
    }

    @Override
    public boolean delete(Customer obj) {
        return false;
    }

    @Override
    public void deleteAll(List<Customer> entities) {

    }

    @Override
    public void saveAll(List<Customer> entities) {

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
}
