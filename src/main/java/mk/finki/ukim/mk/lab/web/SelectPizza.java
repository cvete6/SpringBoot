package mk.finki.ukim.mk.lab.web;


import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.PizzaService;
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

@WebServlet(name = "pizza-servlet", urlPatterns = "/selectPizza.do")

public class SelectPizza extends HttpServlet {

    private PizzaService pizzaService;
    private SpringTemplateEngine springTemplateEngine;

    public SelectPizza(PizzaService pizzaService, SpringTemplateEngine springTemplateEngine) {
        this.pizzaService = pizzaService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("[WP-Log] doPost from SelectPizza");
        HttpSession session = req.getSession();
        String pizzaname = req.getParameter("pizza");
        if(pizzaname==null) resp.sendRedirect("/pizzas");
        Order order =new Order();
        order.setPizzaType(pizzaname);
        session.setAttribute("order",order);
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("pizzaname", pizzaname);
        resp.setContentType("text/html; charset=UTF-8");
        this.springTemplateEngine.process("selectPizzaSize.html", webContext, resp.getWriter());
    }
}
