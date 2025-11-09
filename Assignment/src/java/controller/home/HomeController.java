package controller.home;

import controller.iam.BaseRequiredAuthenticationController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.iam.User;

@WebServlet(urlPatterns = "/home")
public class HomeController extends BaseRequiredAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        
        // ✅ Gửi user qua JSP để hiển thị thông tin
        req.setAttribute("user", user);
        
        // ✅ Forward đến view (ví dụ: home.jsp)
        req.getRequestDispatcher("view/auth/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        // Ở đây bạn có thể xử lý form hoặc filter, nếu có
        doGet(req, resp, user);
    }
}
