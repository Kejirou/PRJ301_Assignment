package controller.iam;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.iam.User;

/**
 * ✅ Base Controller yêu cầu người dùng phải đăng nhập
 * - Nếu có session hợp lệ → gọi tiếp controller con
 * - Nếu chưa đăng nhập → tự động redirect về trang /login
 */
public abstract class BaseRequiredAuthenticationController extends HttpServlet {

    private boolean isAuthenticated(HttpServletRequest req) {
        User u = (User) req.getSession().getAttribute("auth");
        return u != null;
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException;

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (isAuthenticated(req)) {
            User u = (User) req.getSession().getAttribute("auth");
            doPost(req, resp, u);
        } else {
            // ✅ Redirect nhẹ nhàng về trang login
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (isAuthenticated(req)) {
            User u = (User) req.getSession().getAttribute("auth");
            doGet(req, resp, u);
        } else {
            // ✅ Redirect nhẹ nhàng về trang login
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
