package dao;

import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.RequestForLeave;

public class RequestForLeaveDBContext extends DBContext<RequestForLeave> {

    // ✅ Lấy danh sách đơn của bản thân và cấp dưới
    public ArrayList<RequestForLeave> getByEmployeeAndSubodiaries(int eid) {
        ArrayList<RequestForLeave> rfls = new ArrayList<>();
        try {
            connection = getConnection(); // Đừng quên mở connection!
            String sql = """
                WITH Org AS (
                    SELECT *, 0 AS lvl FROM Employee e WHERE e.eid = ?
                    UNION ALL
                    SELECT c.*, o.lvl + 1 AS lvl 
                    FROM Employee c 
                    JOIN Org o ON c.supervisorid = o.eid
                )
                SELECT 
                    r.rid, r.created_by, e.ename AS created_name,
                    r.created_time, r.[from], r.[to],
                    r.reason, r.status,
                    r.processed_by, p.ename AS processed_name
                FROM Org e 
                INNER JOIN RequestForLeave r ON e.eid = r.created_by
                LEFT JOIN Employee p ON p.eid = r.processed_by
            """;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, eid);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                RequestForLeave rfl = new RequestForLeave();
                rfl.setId(rs.getInt("rid"));
                rfl.setCreated_time(rs.getTimestamp("created_time"));
                rfl.setFrom(rs.getDate("from"));
                rfl.setTo(rs.getDate("to"));
                rfl.setReason(rs.getString("reason"));
                rfl.setStatus(rs.getInt("status"));

                Employee created_by = new Employee();
                created_by.setId(rs.getInt("created_by"));
                created_by.setName(rs.getString("created_name"));
                rfl.setCreated_by(created_by);

                int processed_by_id = rs.getInt("processed_by");
                if (!rs.wasNull()) {
                    Employee processed_by = new Employee();
                    processed_by.setId(processed_by_id);
                    processed_by.setName(rs.getString("processed_name"));
                    rfl.setProcessed_by(processed_by);
                }

                rfls.add(rfl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestForLeaveDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return rfls;
    }

    // ✅ Thêm đơn xin nghỉ phép mới
    @Override
    public void insert(RequestForLeave model) {
        try {
            connection = getConnection();
            String sql = """
                INSERT INTO RequestForLeave
                ([created_by], [created_time], [from], [to], [reason], [status])
                VALUES (?, GETDATE(), ?, ?, ?, ?)
            """;

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getCreated_by().getId());
            stm.setDate(2, model.getFrom());
            stm.setDate(3, model.getTo());
            stm.setString(4, model.getReason());
            stm.setInt(5, model.getStatus()); // 1 = InProgress
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RequestForLeaveDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
    }
    
    

    @Override public ArrayList<RequestForLeave> list() { throw new UnsupportedOperationException(); }
    @Override public RequestForLeave get(int id) { throw new UnsupportedOperationException(); }
    @Override public void update(RequestForLeave model) { throw new UnsupportedOperationException(); }
    @Override public void delete(RequestForLeave model) { throw new UnsupportedOperationException(); }

    public void updateStatus(int rid, int status, int processedBy) {
    PreparedStatement stm = null;
    try {
        Connection connection = getConnection();
        String sql = """
            UPDATE RequestForLeave
            SET status = ?, processed_by = ?
            WHERE rid = ?
        """;
        stm = connection.prepareStatement(sql);
        stm.setInt(1, status);
        stm.setInt(2, processedBy);
        stm.setInt(3, rid);
        int rows = stm.executeUpdate();

        if (rows > 0) {
            System.out.println("✅ Updated request #" + rid + " → status " + status);
        } else {
            System.out.println("⚠️ No rows affected for request #" + rid);
        }
    } catch (SQLException ex) {
        Logger.getLogger(RequestForLeaveDBContext.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try { if (stm != null) stm.close(); } catch (SQLException ignore) {}
        closeConnection();
    }
}

    public ArrayList<RequestForLeave> getAgendaByDivision(int leaderId, Date from, Date to) {
    ArrayList<RequestForLeave> list = new ArrayList<>();
    String sql = """
        SELECT r.id, r.reason, r.from_date, r.to_date, r.status,
               e.id AS emp_id, e.name AS emp_name
        FROM RequestForLeave r
        JOIN Employee e ON e.id = r.created_by
        JOIN Division d ON d.id = e.division_id
        WHERE d.leader_id = ? AND r.from_date <= ? AND r.to_date >= ?
        """;
    try (PreparedStatement stm = getConnection().prepareStatement(sql)) {
        stm.setInt(1, leaderId);
        stm.setDate(2, to);
        stm.setDate(3, from);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            RequestForLeave r = new RequestForLeave();
            r.setId(rs.getInt("id"));
            r.setReason(rs.getString("reason"));
            r.setFrom(rs.getDate("from_date"));
            r.setTo(rs.getDate("to_date"));
            r.setStatus(rs.getInt("status"));

            // Gán thông tin người tạo đơn
            model.Employee e = new model.Employee();
            e.setId(rs.getInt("emp_id"));
            e.setName(rs.getString("emp_name"));
            r.setCreated_by(e);

            list.add(r);
        }
    } catch (SQLException ex) {
    }
    return list;
}


}

