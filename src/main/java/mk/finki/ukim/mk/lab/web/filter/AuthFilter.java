/*
package mk.finki.ukim.mk.lab.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;


        String name = (String) httpRequest
                .getSession()
                .getAttribute("username");
        String path = httpRequest.getServletPath();

        if (!"/login".equals(path) && (name == null || name.isEmpty())) {
            httpResp.sendRedirect("/login");

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}
*/
