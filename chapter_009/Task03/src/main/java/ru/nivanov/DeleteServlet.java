package ru.nivanov;

import ru.nivanov.model.TableHtml;
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
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        String id = req.getParameter("id");
        UserStore.getBase().deleteUser(id);

        doGet(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(new TableHtml().getTable());

        writer.append(
                "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n" + "    <title>Delete</title>\n" + "</head>\n" + "<body>\n" + "<br>" + "<form action=").append(
                req.getContextPath()).append("/delete method='post'>").append(
                "Id:  <input type='text' name='id'").append("<br>").append("<input type='submit'").append(
                "</form>").append("<br>").append("<form action=").append(req.getContextPath()).append(
                "/delete method='get'>").append("<button type='submit'>Update records</button>").append(
                "</form>").append("<br>").append("<p><a href=").append(req.getContextPath()).append(
                "/users>Main page</a></p>").append("</body>\n").append("</html>");
        writer.flush();

    }

}
