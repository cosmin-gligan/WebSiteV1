package siit.db;

import siit.config.DatabaseConfig;
import siit.model.User;

import javax.naming.AuthenticationException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    DataSource dataSource;

    public UserDao() {
        this.dataSource = DatabaseConfig.getInstance();
    }

    public User getUserByNameAndPassword(String name, String password) throws AuthenticationException {
        String sql = "SELECT users.* FROM users WHERE TRIM(users.name) = TRIM(?) AND pass = crypt(TRIM(?), pass)";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, name);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){

                return mapDbUser(rs);
            }
            rs.close();

            throw new AuthenticationException("User / parola incorecte!");

        }catch (SQLException e){
            System.out.println(sql);
            System.out.println("Error when getting user: " + e.getMessage());
        }

        return null;
    }

    private User mapDbUser(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getBoolean("isactive"));
    }

}
