package siit.db;


import siit.config.DatabaseConfig;
import siit.model.Customer;
import siit.model.Order;
import siit.services.customers.CustomerService;
import siit.services.orderproducts.OrderProductsService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    CustomerService customerService;
    OrderProductsService orderProductsService;
    DataSource dataSource;

    public OrderDao() {
        customerService = new CustomerService();
        orderProductsService = new OrderProductsService();
        this.dataSource = DatabaseConfig.getInstance();
    }

    public List<Order> getAllOrders(){
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT orders.* FROM orders ORDER BY orders.number";

        try(Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ){
            while(rs.next()) {
                orderList.add(mapDBOrder(rs));
            }
        }catch (SQLException e){
            System.out.println("Error when getting order list" + e.getMessage());
        }
        return orderList;
    }

    public List<Order> getOrderList4CustomerID(int customer_id){
        List<Order> orderList4Customer = new ArrayList<>();

        String sql = "SELECT orders.* FROM orders JOIN customers ON customers.id = orders.customer_id WHERE customers.id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, customer_id);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                orderList4Customer.add(mapDBOrder(rs));
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Error when getting order list 4 customer: " + e.getMessage());
        }
        return orderList4Customer;
    }

    public List<Order> getOrderList4Customer(Customer customer){
        return getOrderList4CustomerID(customer.getId());
    }

    private Order mapDBOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setNumber(rs.getString("number"));
        order.setPlaced(rs.getTimestamp("placed").toLocalDateTime());
        order.setCustomerID(rs.getInt("customer_id"));
        order.setCustomer(customerService.getCustomerByID(rs.getInt("customer_id")));
        order.setOrderProductsList(orderProductsService.getOrderProducts4OrderID(rs.getInt("id")));
        return order;
    }
}
