package siit.services.orderproducts;

import siit.db.OrderProductsDao;
import siit.model.Customer;
import siit.model.Order;
import siit.model.OrderProducts;
import siit.model.Product;

import java.util.List;

public class OrderProductsService implements Requierements4OrderProducts {
    OrderProductsDao orderProductsDao;

    public OrderProductsService() {
        orderProductsDao = new OrderProductsDao();
    }

    @Override
    public List<OrderProducts> getOrderProducts4OrderID(int order_id) {
        return orderProductsDao.getOrderItems4OrderID(order_id);
    }

    @Override
    public List<OrderProducts> getOrderProducts4Order(Order order) {
        return getOrderProducts4OrderID(order.getId());
    }


    @Override
    public List<OrderProducts> getAllOrderProducts4Order() {
        return null;
    }

    @Override
    public void printOrderProducts4Order(Order order) {
        System.out.println("\tList of order items: ");
        for ( OrderProducts orderItem : getOrderProducts4Order(order)){
            System.out.println("\t\t" + orderItem);
        }
    }

    @Override
    public void printAllOrderProducts() {

    }

    @Override
    public List<OrderProducts> getOrderProducts4Product(Product product) {
        return getOrderProducts4ProductID(product.getId());
    }

    @Override
    public List<OrderProducts> getOrderProducts4ProductID(int product_id) {
        return orderProductsDao.getOrderItems4ProductID(product_id);
    }

    @Override
    public List<OrderProducts> getOrderProduct4Customer(Customer customer) {
        return getOrderProduct4CustomerID(customer.getId());
    }

    @Override
    public List<OrderProducts> getOrderProduct4CustomerID(int customer_id) {
        return orderProductsDao.getOrderItems4CustomerID(customer_id);
    }
}
