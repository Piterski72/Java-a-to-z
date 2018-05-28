package ru.nivanov.servlets;

import ru.nivanov.repository.UserRepository;
import ru.nivanov.repository.implrep.UserRepoImpl;

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
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String rolename = req.getParameter("rolename");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String rawMusic = req.getParameter("music");

        String premusic = rawMusic.replaceAll("[\\s]*,[\\s]*", ",");
        String[] music = premusic.split(",");

        HttpSession session = req.getSession(false);
        String role = (String) session.getAttribute("role");

        if (role.equalsIgnoreCase("admin")) {

            UserRepository userRepository = new UserRepoImpl();
            userRepository.updateEntity(Integer.parseInt(id), name, address, rolename, music);

        } else {
            resp.sendRedirect(String.format("%s/mainpage.html", req.getContextPath()));
        }
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("update ok!");
        out.flush();

    }
}
