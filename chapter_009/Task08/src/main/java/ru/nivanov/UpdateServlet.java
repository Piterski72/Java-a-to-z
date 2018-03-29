package ru.nivanov;

import ru.nivanov.model.User;
import ru.nivanov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Nikolay Ivanov on 24.10.2017.
 */
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String rolename = req.getParameter("rolename");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        long date = System.currentTimeMillis();
        String city = req.getParameter("city");
        String country = req.getParameter("country");

        HttpSession session = req.getSession(false);
        int sessionUserID = (int) session.getAttribute("id");
        String role = (String) session.getAttribute("rolename");

        if (!role.equals("admin") && sessionUserID == Integer.parseInt(id)) {

            User oldUser = (User) session.getAttribute("user");
            String userLogin = (String) session.getAttribute("login");
            User updatedUser = new User(name, userLogin, email, city, country, date);
            updatedUser.setPassword(oldUser.getPassword());
            UserStore.getBase().updateUser(id, updatedUser);

        } else if (role.equals("admin")) {
            User updatedUser = new User(name, login, email, city, country, date);
            updatedUser.setPassword(password);
            UserStore.getBase().updateUser(id, updatedUser);
            UserStore.getBase().updateUserRole(id, rolename);

        } else {
            resp.sendRedirect(String.format("%s/mainpage.html", req.getContextPath()));
        }
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("update ok!");
        out.flush();

    }
}
