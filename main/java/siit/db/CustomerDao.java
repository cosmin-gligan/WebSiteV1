package siit.db;

import siit.config.DatabaseConfig;
import siit.model.Customer;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    DataSource dataSource;

    public CustomerDao() {
        this.dataSource = DatabaseConfig.getInstance();
    }

    public ArrayList<Customer> getAllCustomers(){
        String sql = "SELECT customers.* FROM customers ORDER BY customers.name";
        ArrayList<Customer> customersList = new ArrayList<>();

        try(Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()){
                customersList.add(mapDBCustomer(rs));
            }

        }catch (SQLException e){
            System.out.println("Error when getting customers list: " + e.getMessage());
        }

        return customersList;
    }

    public Customer getCustomerByID(int id) {
        String sql = "SELECT * FROM customers WHERE customers.id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                return mapDBCustomer(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //o sa ajunga aici daca nu exista clientul cu id-ul cerut
        return null;
    }

    private Customer mapDBCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getDate("birthday").toLocalDate());
        return customer;
    }

}
