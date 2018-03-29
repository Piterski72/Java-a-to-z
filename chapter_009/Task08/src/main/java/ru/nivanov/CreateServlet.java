package ru.nivanov;

import org.json.simple.JSONObject;
import ru.nivanov.model.User;
import ru.nivanov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Nikolay Ivanov on 24.10.2017.
 */
public class CreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String rolename = req.getParameter("rolename");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        long date = System.currentTimeMillis();
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        int roleid = UserStore.getBase().getRoleId(rolename);
        User newUser = new User(name, login, email, city, country, date);
        newUser.setPassword(password);

        UserStore.getBase().addUser(newUser, roleid);

        JSONObject object = new JSONObject();
        object.put("result", "create ok!");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(object);
        out.flush();
    }
}
