package siit.db;


import siit.config.DatabaseConfig;
import siit.model.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    DataSource dataSource;

    public ProductDao() {
        this.dataSource = DatabaseConfig.getInstance();
    }

    public List<Product> getProductsList(){
        List<Product> productList = new ArrayList<>();

        String sql = "SELECT products.* FROM products ORDER BY products.name";

        try (Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ){
            while(rs.next()) {
                productList.add(mapDBProduct(rs));
            }
        }catch (SQLException e ){
            System.out.println("Error when getting products list " + e.getMessage());
        }

        return productList;
    }

    public Product getProductByID(int id){
        String sql = "SELECT products.* FROM products WHERE products.id = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                return mapDBProduct(rs);
            }
        }catch (SQLException e){
            System.out.println("Error when getting product by id: " + e.getMessage());
        }
        return null;
    }

    private Product mapDBProduct(ResultSet rs) throws SQLException {
        return new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("weight"), rs.getDouble("price"));
    }
}
