package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.apache.catalina.servlets.DefaultServlet;
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

@WebServlet(urlPatterns = "/PizzaOrder.do")
public class PizzaOrder extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    public PizzaOrder(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[WP-Log] doGet from PizzaOrder");

        HttpSession session = req.getSession();
        Order orderPresent=(Order)session.getAttribute("order");
        if(orderPresent==null) resp.sendRedirect("/pizzas");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("[WP-Log] doPost from PizzaOrder");

        HttpSession session = req.getSession();

        Order orderPresent=(Order)session.getAttribute("order");
        if(orderPresent==null) resp.sendRedirect("/pizzas");

        String pizza_size = req.getParameter("pizza_size");
        Order order= (Order) req.getSession().getAttribute("order");
        order.setPizzaSize(pizza_size);
        req.getSession().setAttribute("order", order);
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("pizzaname", order.getPizzaType());
        webContext.setVariable("pizza_size",pizza_size);
        resp.setContentType("text/html; charset=UTF-8");
        this.springTemplateEngine.process("deliveryInfo.html", webContext, resp.getWriter());

    }

}
