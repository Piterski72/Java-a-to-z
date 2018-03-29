package ru.nivanov;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Nikolay Ivanov on 13.12.2017.
 */
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        session.removeAttribute("login");
        session.invalidate();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("You are successfully logged out!");
        out.close();
        req.getRequestDispatcher("/newlogpage.html").forward(req, resp);
    }
}
