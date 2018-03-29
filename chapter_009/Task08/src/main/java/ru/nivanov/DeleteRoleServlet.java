package ru.nivanov;

import org.json.simple.JSONObject;
import ru.nivanov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Nikolay Ivanov on 12.12.2017.
 */
public class DeleteRoleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String rolename = req.getParameter("rolename");
        int roleid = UserStore.getBase().getRoleId(rolename);
        UserStore.getBase().deleteRole(String.valueOf(roleid));

        JSONObject object = new JSONObject();
        object.put("result", "delete role ok!");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(object);
        out.flush();
    }
}
