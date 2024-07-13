package app.dao.daoInterfaceImpl;

import app.dao.daoInterface.CustomerDAO;
import app.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CustomerDAOImpl implements CustomerDAO {

    private List<Customer> customers;

    public CustomerDAOImpl() {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "Customer A", "a@aol.com", 45, new ArrayList<>()));
        customers.add(new Customer(2, "Customer B", "b@bol.com", 31, new ArrayList<>()));
        customers.add(new Customer(3, "Customer B", "b2@aol.com", 38, new ArrayList<>()));
        customers.add(new Customer(4, "Customer C", "c@aol.com", 77, new ArrayList<>()));
        customers.add(new Customer(5, "Customer D", "d@aol.com", 19, new ArrayList<>()));
        customers.add(new Customer(6, "Customer E", "e@aol.com", 24, new ArrayList<>()));
        customers.add(new Customer(7, "Customer F", "f@aol.com", 65, new ArrayList<>()));
    }

    @Override
    public Optional<List<Customer>> findByName(String name) {
        List<Customer> requredCustomersList = customers.stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList());
        if (!requredCustomersList.isEmpty()) return Optional.of(requredCustomersList);
        return Optional.empty();
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        Customer requestedCustomer = customers.stream().filter(c -> c.getEmail().equals(email)).findFirst().orElse(null);
        if (requestedCustomer != null) return Optional.of(requestedCustomer);
        return Optional.empty();
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
    public Optional<List<Customer>> findAll() {
        if (customers != null) return Optional.of(customers);
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> deleteById(long id) {
        Customer requiredCustomer = customers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (requiredCustomer != null) {
            customers.remove(requiredCustomer);
            return Optional.of(true);
        }
        return Optional.of(false);
    }

    @Override
    public Optional<Customer> getOne(long id) {
        Customer customer = customers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (customer != null) {
            return Optional.of(customer);
        } else return Optional.empty();
    }
}
