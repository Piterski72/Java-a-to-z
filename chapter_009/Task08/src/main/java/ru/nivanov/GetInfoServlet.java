package ru.nivanov;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import ru.nivanov.model.Role;
import ru.nivanov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Nikolay Ivanov on 24.10.2017.
 */
public class GetInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String role = (String) session.getAttribute("rolename");
        Map<Integer, Role> roles;
        if (!role.equals("admin")) {
            int roleid = UserStore.getBase().getRoleId(role);
            roles = new ConcurrentHashMap<>();
            roles.put(roleid, new Role(role));

        } else {
            roles = UserStore.getBase().showRoles();
        }
        List<String> cities = UserStore.getBase().showCities();
        List<String> countries = UserStore.getBase().showCountries();
        Gson gson = new Gson();
        String jsonRl = gson.toJson(roles);
        String jsonCt = gson.toJson(cities);
        String jsonCn = gson.toJson(countries);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("roles", jsonRl);
        jsonObject.put("cities", jsonCt);
        jsonObject.put("countries", jsonCn);
        jsonObject.put("login", session.getAttribute("login").toString());
        jsonObject.put("rolename", session.getAttribute("rolename").toString());

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.println(jsonObject);
        writer.flush();

    }

    @Override
    public void destroy() {
        UserStore.getBase().shutDownDataSource();
    }
}
