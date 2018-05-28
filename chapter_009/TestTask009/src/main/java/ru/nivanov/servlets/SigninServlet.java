package ru.nivanov.servlets;

import org.json.simple.JSONObject;
import ru.nivanov.entity.Role;
import ru.nivanov.repository.mapper.Mapper;
import ru.nivanov.repository.mapper.StringToRoleMapper;

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


        String role = req.getParameter("role");
        Mapper<String, Role> mapper = new StringToRoleMapper();

        if (!mapper.map(role).getRolename().equals("empty")) {
            HttpSession session = req.getSession();
            session.setAttribute("role", role);

            resp.sendRedirect(String.format("%s/mainpage.html", req.getContextPath()));
        } else {
            resp.sendRedirect(String.format("%s/logpage.html", req.getContextPath()));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "invalid role!");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(jsonObject);
        out.flush();
    }
}
