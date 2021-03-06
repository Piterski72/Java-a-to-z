package ru.nivanov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nivanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created by Nikolay Ivanov on 24.10.2017.
 */
public class UsersServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UsersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();


        Map<Integer, User> map = DbaseHandler.getBase().showUsers();
        for (Map.Entry entry : map.entrySet()) {
            int id = (int) entry.getKey();
            User user = (User) entry.getValue();
            writer.println(
                    String.format("id= %d name= %s login= %s email= %s date= %s", id, user.getName(), user.getLogin(),
                            user.getEmail(), new Date(user.getCreateDate())));

        }
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        long date = System.currentTimeMillis();

        DbaseHandler.getBase().addUser(new User(name, login, email, date));

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String id = req.getParameter("id");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        long date = System.currentTimeMillis();

        User newUser = new User(name, login, email, date);

        DbaseHandler.getBase().updateUser(id, newUser);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        DbaseHandler.getBase().deleteUser(id);
    }


}
