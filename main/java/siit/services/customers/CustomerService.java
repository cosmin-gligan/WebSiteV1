package siit.services.customers;

import siit.db.CustomerDao;
import siit.model.Customer;

import java.util.List;

public class CustomerService implements Requirements4Customer {
    private CustomerDao customerDao;

    public CustomerService(){
        customerDao = new CustomerDao();
    }


    @Override
    public Customer getCustomerByID(int id) {
        return customerDao.getCustomerByID(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }
}
