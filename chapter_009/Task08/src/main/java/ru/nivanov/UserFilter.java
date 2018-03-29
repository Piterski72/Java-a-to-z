package ru.nivanov;

import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Nikolay Ivanov on 27.03.2018.
 */
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute("rolename");
        if (role.equals("admin")) {
            filterChain.doFilter(request, response);
        } else {
            JSONObject object = new JSONObject();
            object.put("result", "cannot perform operation, you are not admin");
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println(object);
            out.flush();
        }
    }

    @Override
    public void destroy() {

    }
}
