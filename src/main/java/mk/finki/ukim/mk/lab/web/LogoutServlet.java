package mk.finki.ukim.mk.lab.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    /*
     * We use the service method since it is invoked for all HTTP methods
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("[WP-Log] service from LogoutServlet");
        req.getSession().invalidate();
        resp.sendRedirect("/pizzas");
    }
}
