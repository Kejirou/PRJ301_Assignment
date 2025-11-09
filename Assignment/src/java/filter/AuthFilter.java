package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI();
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("auth") != null);
        boolean isLoginPage = path.endsWith("/Login") || path.contains("/css/") || path.contains("/images/");

        if (isLoggedIn || isLoginPage) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/Login");
        }
    }
}
