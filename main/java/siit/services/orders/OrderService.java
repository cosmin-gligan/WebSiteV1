package siit.services.orders;

import siit.db.OrderDao;
import siit.model.Customer;
import siit.model.Order;
import siit.services.orderproducts.OrderProductsService;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements Requirements4Orders {
    OrderDao orderDao;
    OrderProductsService orderProductsService;

    public OrderService() {
        orderDao = new OrderDao();
        orderProductsService = new OrderProductsService();
    }

    @Override
    public List<Order> getAllOrders4Customer(Customer customer) {
        return orderDao.getOrderList4Customer(customer);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public void printOrderList() {
        System.out.println("\nList of orders:");
        for ( Order order : getAllOrders() ){
            System.out.println("\t" + order);
        }
    }

    @Override
    public void printOrderList4Customer(Customer customer) {
        List<Order> orderList = new ArrayList<>(getAllOrders4Customer(customer));
        System.out.println("\nOrder list for " + customer.getName());
        for (Order order : orderList){
            System.out.println("\n\t" + order);
            orderProductsService.printOrderProducts4Order(order);
        }
    }
}
