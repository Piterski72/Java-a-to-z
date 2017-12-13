package ru.nivanov;

import ru.nivanov.model.User;
import ru.nivanov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Nikolay Ivanov on 01.12.2017.
 */
public class SigninServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (UserStore.getBase().isCredentional(login, password)) {

            User user = UserStore.getBase().getCredentionalUser();
            int userId = UserStore.getBase().getUserId(user);

            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("login", login);
            session.setAttribute("rolename", user.getRole().getName());
            session.setAttribute("id", userId);
            resp.sendRedirect(String.format("%s/", req.getContextPath()));

        } else {
            req.setAttribute("error", "Credentional invalid");
            doGet(req, resp);

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }
}
