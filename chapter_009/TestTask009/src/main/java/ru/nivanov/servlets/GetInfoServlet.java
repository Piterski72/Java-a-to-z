package ru.nivanov.servlets;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import ru.nivanov.baseutils.PostgresBaseUtils;
import ru.nivanov.dao.RoleDao;
import ru.nivanov.dao.impl.PostgresRoleDao;
import ru.nivanov.entity.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Nikolay Ivanov on 24.10.2017.
 */
public class GetInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String role = (String) session.getAttribute("role");
        Collection<Role> roles;

        if (!role.equalsIgnoreCase("admin")) {

            roles = new CopyOnWriteArraySet<>();
            Role current = new Role();
            current.setRolename(role);
            roles.add(current);

        } else {
            RoleDao roleDao = new PostgresRoleDao();
            roles = roleDao.getAll();
        }

        Gson gson = new Gson();
        String jsonRl = gson.toJson(roles);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("roles", jsonRl);
        jsonObject.put("rolename", role);

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.println(jsonObject);
        writer.flush();
    }

    @Override
    public void destroy() {
        PostgresBaseUtils.getBase().shutDownDataSource();
    }
}
