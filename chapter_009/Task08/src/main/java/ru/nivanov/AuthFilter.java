package ru.nivanov;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Nikolay Ivanov on 04.12.2017.
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        if (request.getRequestURI().contains("/newlogpage") || request.getRequestURI().contains(
                "/signin") || request.getRequestURI().contains("/img")) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("login") == null) {
                response.sendRedirect(String.format("%s/newlogpage.html", request.getContextPath()));
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
