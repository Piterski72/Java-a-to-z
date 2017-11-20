package ru.nivanov;

import ru.nivanov.model.TableHtml;
import ru.nivanov.model.User;
import ru.nivanov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Nikolay Ivanov on 24.10.2017.
 */
public class CreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        long date = System.currentTimeMillis();

        UserStore.getBase().addUser(new User(name, login, email, date));

        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(new TableHtml().getTable());

        writer.append(
                "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n" + "    <title>Create</title>\n" + "</head>\n" + "<body>\n" + "<br>" + "<form action=").append(
                req.getContextPath()).append("/create method='post'>").append(
                "Name: <input type='text' name='name'").append("<br>").append(
                "Login: <input type='text' name='login'").append("<br>").append(
                "Email: <input type='text' name='email'").append("<br>").append("<input type='submit'").append(
                "</form>").append("<br>").append("<form action=").append(req.getContextPath()).append(
                "/create method='get'>").append("<button type='submit'>Update records</button>").append(
                "</form>").append("<br>").append("<p><a href=").append(req.getContextPath()).append(
                "/users>Main page</a></p>").append("</body>\n").append("</html>");
        writer.flush();
    }

}
