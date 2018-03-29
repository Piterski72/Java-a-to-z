package ru.nivanov;

import com.google.gson.Gson;
import ru.nivanov.model.User;
import ru.nivanov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Nikolay Ivanov on 24.10.2017.
 */
public class UsersViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<Integer, User> userMap = UserStore.getBase().showUsers();
        HttpSession session = req.getSession(false);
        Gson gson = new Gson();
        String json = "";
        String rolename = (String) session.getAttribute("rolename");
        if (rolename.equals("admin")) {
            json = gson.toJson(userMap);
        } else if (session.getAttribute("login") != null && !rolename.equals("admin")) {
            int userId = (int) session.getAttribute("id");
            User user = userMap.get(userId);
            Map<Integer, User> singleUser = new ConcurrentHashMap<>();
            singleUser.put(userId, user);
            json = gson.toJson(singleUser);
        }
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.println(json);
        writer.flush();
    }

    @Override
    public void destroy() {
        UserStore.getBase().shutDownDataSource();
    }
}
