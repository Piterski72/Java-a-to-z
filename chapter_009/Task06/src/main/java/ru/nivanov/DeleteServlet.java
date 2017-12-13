package ru.nivanov;

import ru.nivanov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Nikolay Ivanov on 24.10.2017.
 */
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        String id = req.getParameter("id");
        UserStore.getBase().deleteUser(id);

        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (!session.getAttribute("rolename").equals("admin")) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.getRequestDispatcher("/WEB-INF/views/delete.jsp").forward(req, resp);
        }
    }
}
