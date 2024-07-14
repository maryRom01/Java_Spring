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
        customers.add(new Customer(1, "Customer A", "a@bol.com", 31, new ArrayList<>()));
        customers.add(new Customer(2, "Customer B", "b@bol.com", 31, new ArrayList<>()));
        customers.add(new Customer(3, "Customer B", "b2@aol.com", 38, new ArrayList<>()));
        customers.add(new Customer(4, "Customer C", "c@aol.com", 77, new ArrayList<>()));
        customers.add(new Customer(5, "Customer D", "d@aol.com", 19, new ArrayList<>()));
        customers.add(new Customer(6, "Customer E", "e@aol.com", 24, new ArrayList<>()));
        customers.add(new Customer(7, "Customer F", "f@aol.com", 65, new ArrayList<>()));
        long lastExistingId = customers.get(customers.size() - 1).getId();
        Customer.setSerialVersionUID(lastExistingId + 1);
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
    public Optional<Customer> findById(long id) {
        Customer requestedCustomer = customers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (requestedCustomer != null) return Optional.of(requestedCustomer);
        return Optional.empty();
    }

    public Optional<Customer> getCustomerIfExists(Customer customer) {
        List<Customer> cc = customers.stream()
                .filter(c -> c.getName().equals(customer.getName()))
                .filter(c -> c.getEmail().equals(customer.getEmail()))
                .filter(c -> c.getAge().equals(customer.getAge()))
                .toList();
        if (!cc.isEmpty()) return Optional.of(cc.get(0));
        return Optional.empty();
    }

    @Override
    public Optional<Customer> save(Customer customer) {
            getCustomerIfExists(customer).ifPresent(value ->
                    {
                            value.setName(customer.getName());
                            value.setEmail(customer.getEmail());
                            value.setAge(customer.getAge());
                            value.setAccounts(customer.getAccounts());
                    }
            );
            if (getCustomerIfExists(customer).isEmpty()) {
                customers.add(customer);
            }
            return Optional.of(customer);
    }

    @Override
    public Optional<Boolean> delete(Customer obj) {
        return Optional.empty();
    }

    @Override
    public void deleteAll(List<Customer> entities) {

    }

    @Override
    public Optional<List<Customer>> saveAll(List<Customer> customersToBeAdded) {
        if (!customers.isEmpty() && !customersToBeAdded.isEmpty()) {
            customersToBeAdded.forEach(customer -> {
                save(customer);
            });
        }
        if (customers.isEmpty() && !customersToBeAdded.isEmpty()) {
            customers.addAll(customersToBeAdded);
        }
        return Optional.of(customers);
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
