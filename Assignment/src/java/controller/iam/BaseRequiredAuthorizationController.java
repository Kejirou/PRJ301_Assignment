package controller.iam;

import dao.RoleDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.iam.Feature;
import model.iam.Role;
import model.iam.User;

public abstract class BaseRequiredAuthorizationController extends BaseRequiredAuthenticationController {

    private boolean isAuthorized(HttpServletRequest req, User user) {
        if (user.getRoles().isEmpty()) {
            RoleDBContext db = new RoleDBContext();
            user.setRoles(db.getByUserId(user.getId()));
            req.getSession().setAttribute("auth", user);
        }

        String url = req.getServletPath(); // ví dụ: /division/agenda
        for (Role role : user.getRoles()) {
            for (Feature feature : role.getFeatures()) {
                if (feature.getUrl().equalsIgnoreCase(url)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected abstract void processPost(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException;

    protected abstract void processGet(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        if (isAuthorized(req, user)) {
            processPost(req, resp, user);
        } else {
            req.setAttribute("message", "⛔ You don’t have permission to access this page.");
            req.getRequestDispatcher("/view/util/message.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        if (isAuthorized(req, user)) {
            processGet(req, resp, user);
        } else {
            req.setAttribute("message", "⛔ You don’t have permission to access this page.");
            req.getRequestDispatcher("/view/util/message.jsp").forward(req, resp);
        }
    }
}
