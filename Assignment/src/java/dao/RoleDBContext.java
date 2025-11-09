package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.iam.Feature;
import model.iam.Role;

public class RoleDBContext extends DBContext<Role> {

    public ArrayList<Role> getByUserId(int uid) {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            // ✅ đảm bảo connection được mở
            connection = getConnection();

            String sql = """
                SELECT r.rid, r.rname, f.fid, f.furl
                FROM [Role] r
                INNER JOIN [User_Role] ur ON r.rid = ur.rid
                INNER JOIN [Feature_Role] fr ON fr.rid = r.rid
                INNER JOIN [Feature] f ON f.fid = fr.fid
                WHERE ur.uid = ?
            """;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, uid);
            ResultSet rs = stm.executeQuery();

            Role currentRole = null;
            while (rs.next()) {
                int rid = rs.getInt("rid");
                if (currentRole == null || currentRole.getId() != rid) {
                    currentRole = new Role();
                    currentRole.setId(rid);
                    currentRole.setName(rs.getString("rname"));
                    currentRole.setFeatures(new ArrayList<>());
                    roles.add(currentRole);
                }

                Feature f = new Feature();
                f.setId(rs.getInt("fid"));
                f.setUrl(rs.getString("furl"));
                currentRole.getFeatures().add(f);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return roles;
    }

    @Override public ArrayList<Role> list() { throw new UnsupportedOperationException(); }
    @Override public Role get(int id) { throw new UnsupportedOperationException(); }
    @Override public void insert(Role model) { throw new UnsupportedOperationException(); }
    @Override public void update(Role model) { throw new UnsupportedOperationException(); }
    @Override public void delete(Role model) { throw new UnsupportedOperationException(); }
}
