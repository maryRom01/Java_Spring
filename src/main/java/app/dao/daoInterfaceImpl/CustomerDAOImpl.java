package app.dao.daoInterfaceImpl;

import app.dao.daoInterface.CustomerDAO;
import app.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
        return customers;
    }

    @Override
    public boolean deleteById(long id) {
        Customer requiredCustomer = customers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (requiredCustomer != null) {
            customers.remove(requiredCustomer);
            return true;
        }
        return false;
    }

    @Override
    public Customer getOne(long id) {
        return customers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
}
