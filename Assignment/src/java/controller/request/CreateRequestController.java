package controller.request;

import dao.RequestForLeaveDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import model.RequestForLeave;
import model.iam.User;

@WebServlet("/request/create")
public class CreateRequestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Hiển thị form tạo đơn nghỉ phép
        req.getRequestDispatcher("/view/request/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String from_raw = req.getParameter("from");
        String to_raw = req.getParameter("to");
        String reason = req.getParameter("reason");

        // Lấy thông tin user đăng nhập
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("auth");

        // Tạo đối tượng request
        RequestForLeave r = new RequestForLeave();
        r.setFrom(Date.valueOf(from_raw));
        r.setTo(Date.valueOf(to_raw));
        r.setReason(reason);
        r.setStatus(1); // In Progress
        r.setCreated_by(user.getEmployee());

        // Lưu vào DB
        RequestForLeaveDBContext db = new RequestForLeaveDBContext();
        db.insert(r);

        // Quay về danh sách hoặc dashboard
        resp.sendRedirect(req.getContextPath() + "/request/list");
    }
}
