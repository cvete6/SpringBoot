package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/ConfirmationInfo.do")
public class ConfirmationInfoServlet extends HttpServlet {
    private final OrderService orderService;
    private final SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfoServlet(OrderService orderService, SpringTemplateEngine springTemplateEngine) {
        this.orderService = orderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[WP-Log] doGet from ConfirmationInfoServlet");

        HttpSession session = req.getSession();

        Order orderPresent=(Order)session.getAttribute("order");
        if(orderPresent==null) resp.sendRedirect("/pizzas");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("[WP-Log] doPost from ConfirmationInfoServlet");

        HttpSession session = req.getSession();
        Order orderPresent=(Order)session.getAttribute("order");
        if(orderPresent==null) resp.sendRedirect("/pizzas");
        String clientAddress= (String) req.getParameter("clientAddress");
        String clientName= (String) req.getParameter("clientName");
        Order clientOrder=(Order)req.getSession().getAttribute("order");
        String pizzaname= clientOrder.getPizzaType();
        String pizzasize=clientOrder.getPizzaSize();

        Order newOrder = orderService.placeOrder(pizzaname,clientName,clientAddress);

        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("pizzaname", pizzaname);
        webContext.setVariable("pizza_size", pizzasize);
        webContext.setVariable("clientAddress",clientAddress);
        webContext.setVariable("clientName",clientName);
        webContext.setVariable("browser",req.getHeader("User-Agent"));
        webContext.setVariable("ipAdress",req.getRemoteAddr());
        resp.setContentType("text/html; charset=UTF-8");
        this.springTemplateEngine.process("ConfirmationInfo.html", webContext, resp.getWriter());

    }

}
