package controller.request;

import dao.RequestForLeaveDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import model.RequestForLeave;
import model.iam.User;

@WebServlet("/request/list")
public class ListRequestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User u = (User) req.getSession().getAttribute("auth");

        RequestForLeaveDBContext db = new RequestForLeaveDBContext();
        ArrayList<RequestForLeave> requests = db.getByEmployeeAndSubodiaries(u.getEmployee().getId());

        req.setAttribute("requests", requests);
        req.getRequestDispatcher("/view/request/list.jsp").forward(req, resp);
    }
}
