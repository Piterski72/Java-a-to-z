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
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String id = req.getParameter("id");
        String roleid = req.getParameter("roleid");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        long date = System.currentTimeMillis();

        HttpSession session = req.getSession(false);
        int sessionUserID = (int) session.getAttribute("id");

        if (!session.getAttribute("rolename").equals("admin") && sessionUserID == Integer.parseInt(id)) {

            User oldUser = (User) session.getAttribute("user");
            String userLogin = (String) session.getAttribute("login");

            User updatedUser = new User(name, userLogin, email, date);

            updatedUser.setPassword(oldUser.getPassword());

            UserStore.getBase().updateUser(id, updatedUser);
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else if (session.getAttribute("rolename").equals("admin")) {
            User updatedUser = new User(name, login, email, date);
            updatedUser.setPassword(password);
            UserStore.getBase().updateUser(id, updatedUser);
            UserStore.getBase().updateUserRole(id, roleid);
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, Role> roles = UserStore.getBase().showRoles();
        req.setAttribute("rolelist", roles);
        req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
    }
}
