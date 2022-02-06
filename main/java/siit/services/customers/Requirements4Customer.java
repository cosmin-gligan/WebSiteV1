package siit.services.customers;

import siit.model.Customer;

import java.util.List;

public interface Requirements4Customer {
    Customer getCustomerByID(int id);
    List<Customer> getAllCustomers();
}
