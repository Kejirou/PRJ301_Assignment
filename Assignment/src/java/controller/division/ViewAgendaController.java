package controller.division;

import java.sql.Date;
import dao.RequestForLeaveDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.RequestForLeave;
import model.iam.User;

@WebServlet("/division/agenda") 
public class ViewAgendaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("auth");
        int leaderId = user.getEmployee().getId();

        // Hiển thị 7 ngày kế tiếp
        LocalDate today = LocalDate.now();
        Date from = Date.valueOf(today);
        Date to = Date.valueOf(today.plusDays(6));

        // Lấy dữ liệu từ DB
        RequestForLeaveDBContext db = new RequestForLeaveDBContext();
        ArrayList<RequestForLeave> agenda = db.getAgendaByDivision(leaderId, from, to);

        // Tạo list 7 ngày để render trên JSP
        List<LocalDate> days = IntStream.rangeClosed(0, 6)
                .mapToObj(today::plusDays)
                .collect(Collectors.toList());

        req.setAttribute("agenda", agenda);
        req.setAttribute("days", days);
        req.setAttribute("from", from);
        req.setAttribute("to", to);

        req.getRequestDispatcher("/view/division/agenda.jsp").forward(req, resp);
    }
}
