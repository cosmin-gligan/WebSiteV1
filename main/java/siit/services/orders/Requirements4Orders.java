package siit.services.orders;

import siit.model.Customer;
import siit.model.Order;

import java.util.List;

public interface Requirements4Orders {

    List<Order> getAllOrders4Customer(Customer customer);
    List<Order> getAllOrders();
    void printOrderList();
    void printOrderList4Customer(Customer customer);

}
