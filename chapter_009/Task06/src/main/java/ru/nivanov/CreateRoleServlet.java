package ru.nivanov;

import ru.nivanov.model.Role;
import ru.nivanov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Nikolay Ivanov on 12.12.2017.
 */
public class CreateRoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (!session.getAttribute("rolename").equals("admin")) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            Map<Integer, Role> roles = UserStore.getBase().showRoles();
            req.setAttribute("rolelist", roles);
            req.getRequestDispatcher("/WEB-INF/views/createrole.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String rolename = req.getParameter("rolename");
        Role newRole = new Role(rolename);

        UserStore.getBase().addRole(newRole);

        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
