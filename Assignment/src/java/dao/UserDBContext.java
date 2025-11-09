package dao;

import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.iam.User;

public class UserDBContext extends DBContext<User> {

    public User get(String username, String password) {
        try {
            connection = getConnection();
            String sql = """
    SELECT u.username, u.displayname, u.password, 
           r.id AS role_id, r.name AS role_name,
           e.id AS emp_id, e.name AS emp_name,
           d.id AS div_id, d.name AS div_name
    FROM [User] u
    JOIN Role r ON u.role_id = r.id
    JOIN Employee e ON u.emp_id = e.id
    JOIN Division d ON e.division_id = d.id
    WHERE u.username = ? AND u.password = ?
""";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("uid"));
                u.setUsername(rs.getString("username"));
                u.setDisplayname(rs.getString("displayname"));

                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getString("ename"));
                u.setEmployee(e);

                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override public ArrayList<User> list() { throw new UnsupportedOperationException(); }
    @Override public User get(int id) { throw new UnsupportedOperationException(); }
    @Override public void insert(User model) { throw new UnsupportedOperationException(); }
    @Override public void update(User model) { throw new UnsupportedOperationException(); }
    @Override public void delete(User model) { throw new UnsupportedOperationException(); }

    public User checkLogin(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
