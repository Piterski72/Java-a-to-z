package ru.nivanov;

import ru.nivanov.model.Role;
import ru.nivanov.model.User;
import ru.nivanov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Nikolay Ivanov on 24.10.2017.
 */
public class CreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String roleid = req.getParameter("roleid");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        long date = System.currentTimeMillis();

        User newUser = new User(name, login, email, date);
        newUser.setPassword(password);
        UserStore.getBase().addUser(newUser, roleid);

        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        Map<Integer, Role> roles = UserStore.getBase().showRoles();
        req.setAttribute("rolelist", roles);

        if (!session.getAttribute("rolename").equals("admin")) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
        }


    }
}
