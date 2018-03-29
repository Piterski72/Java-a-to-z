package ru.nivanov;

import org.json.simple.JSONObject;
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
 * Created by Nikolay Ivanov on 01.12.2017.
 */
public class SigninServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (UserStore.getBase().isCredentional(login, password)) {
            HttpSession session = req.getSession();
            User user = UserStore.getBase().getCredentionalUser();
            int userId = UserStore.getBase().getUserId(user);

            session.setAttribute("user", user);
            session.setAttribute("login", login);
            session.setAttribute("rolename", user.getRole().getName());
            session.setAttribute("id", userId);
            resp.sendRedirect(String.format("%s/mainpage.html", req.getContextPath()));
        } else {
            resp.sendRedirect(String.format("%s/newlogpage.html", req.getContextPath()));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "invalid login or password data!");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(jsonObject);
        out.flush();
    }
}
