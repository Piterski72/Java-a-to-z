package ru.nivanov;

import org.json.simple.JSONObject;
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
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String role = (String) session.getAttribute("rolename");
        String id = req.getParameter("id");
        JSONObject object = new JSONObject();
        if (role.equals("admin")) {
            UserStore.getBase().deleteUser(id);
            object.put("result", "success");
        } else {
            object.put("result", "cannot perform delete, you are not admin");
        }
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(object);
        out.flush();
    }
}
