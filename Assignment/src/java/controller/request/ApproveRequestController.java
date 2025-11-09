package controller.request;

import dao.RequestForLeaveDBContext;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.iam.User;

@WebServlet("/request/approve")
public class ApproveRequestController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int rid = Integer.parseInt(req.getParameter("rid"));
        RequestForLeaveDBContext db = new RequestForLeaveDBContext();

        // ✅ Lấy thông tin người duyệt
        User user = (User) req.getSession().getAttribute("auth");
        int processedBy = user.getEmployee().getId();

        db.updateStatus(rid, 2, processedBy); // 2 = Approved
        resp.sendRedirect(req.getContextPath() + "/request/list");
    }
}

