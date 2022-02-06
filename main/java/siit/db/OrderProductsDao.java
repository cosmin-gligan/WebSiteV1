package siit.db;



import siit.config.DatabaseConfig;
import siit.model.Order;
import siit.model.OrderProducts;
import siit.services.producs.ProductService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProductsDao {
    ProductService productService;
    DataSource dataSource;
    public OrderProductsDao() {
        this.dataSource = DatabaseConfig.getInstance();
        productService = new ProductService();
    }

    public List<OrderProducts> getOrderItems4Order(Order order){
        return getOrderItems4OrderID(order.getId());
    }

    public List<OrderProducts> getOrderItems4OrderID (int order_id){
        List<OrderProducts> orderProductsList = new ArrayList<>();

        String sql = "SELECT orders_products.* FROM orders_products JOIN orders ON orders.id = orders_products.order_id WHERE orders.id = ?";

        //mergea si asa, apareau probleme daca aveam inregistrari orfane in orders_products
        //String sql = "SELECT orders_products.* FROM orders_products WHERE orders_products.order_id = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, order_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){

                orderProductsList.add(mapDBOrderProduct(rs));

            }
            rs.close();

        }catch (SQLException e){
            System.out.println("SQL error when get items 4 order: " + e.getMessage());
        }

        return orderProductsList;
    }

    //nu prea isi are sensul in formulare de culegere date, mai degraba pt. rapoarte ( vanzari pe produs de ex )
    public List<OrderProducts> getOrderItems4ProductID(int product_id){
        List<OrderProducts> orderProductsList = new ArrayList<>();

        String sql = "SELECT orders_products.* FROM orders_products JOIN products ON products.id = orders_products.product_id WHERE products.id = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, product_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){

                orderProductsList.add(mapDBOrderProduct(rs));

            }
            rs.close();

        }catch (SQLException e){
            System.out.println("SQL error when getting order items 4 productID: " + e.getMessage());
        }

        return orderProductsList;
    }

    //nu prea isi are sensul in formulare de culegere date, mai degraba pt. rapoarte ( vanzari pe produs de ex )
    public List<OrderProducts> getOrderItems4CustomerID(int customer_id){
        List<OrderProducts> orderProductsList = new ArrayList<>();

        String sql = "SELECT orders_products.* FROM orders_products JOIN orders ON orders.id = orders_products.order_id JOIN customers ON  customers.id = orders.customer_id \n" +
                "WHERE customers.id = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, customer_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){

                orderProductsList.add(mapDBOrderProduct(rs));

            }
            rs.close();

        }catch (SQLException e){
            System.out.println("SQL error when getting order items 4 customerID: " + e.getMessage());
        }

        return orderProductsList;
    }

    private OrderProducts mapDBOrderProduct(ResultSet rs) throws SQLException {
        OrderProducts orderProducts = new OrderProducts();
        orderProducts.setId(rs.getInt("id"));
        orderProducts.setOrderID(rs.getInt("order_id"));
        orderProducts.setProductID(rs.getInt("product_id"));
        orderProducts.setQuantity(rs.getDouble("quantity"));

        orderProducts.setProduct(productService.getProductByID(rs.getInt("product_id")));

        return orderProducts;
    }


}
