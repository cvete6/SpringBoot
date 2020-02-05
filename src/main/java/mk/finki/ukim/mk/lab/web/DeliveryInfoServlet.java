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
import java.util.List;

@WebServlet(urlPatterns = "/DeliveryInfo.do")
public class DeliveryInfoServlet extends HttpServlet {
    private final OrderService orderService;
    private final SpringTemplateEngine springTemplateEngine;

    public DeliveryInfoServlet(OrderService orderService, SpringTemplateEngine springTemplateEngine) {
        this.orderService = orderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[WP-Log] doGet from DeliveryInfoServlet");

        HttpSession session = req.getSession();

        Order orderPresent=(Order)session.getAttribute("order");
        if(orderPresent==null) resp.sendRedirect("/pizzas");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("[WP-Log] doPost from DeliveryInfoServlet");

        HttpSession session = req.getSession();
        Order orderPresent=(Order)session.getAttribute("order");
        if(orderPresent==null) resp.sendRedirect("/pizzas");

        String  clientName=req.getParameter("clientName");
        session.setAttribute("clientName",clientName);

        String  clientAddress=req.getParameter("clientAddress");
        session.setAttribute("clientAddress",clientAddress);


        Order order= (Order) req.getSession().getAttribute("order");
        order.setClientName(clientName);
        order.setClientAddress(clientAddress);
        req.getSession().setAttribute("order", order);

        resp.sendRedirect("/ConfirmationInfo.do");

    }

}
