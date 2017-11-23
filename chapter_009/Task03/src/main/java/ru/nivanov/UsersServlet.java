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
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(new TableHtml().getTable());

        writer.append(
                "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n" + "    <title>Menu</title>\n" + "</head>\n" + "<body>\n" + "<br>" + "<form action=").append(
                req.getContextPath()).append("/update>").append("<button type='submit'>Edit user</button>").append(
                "</form>").append("<form action=").append(req.getContextPath()).append("/delete>").append(
                "<button type='submit'>Delete user</button>").append("</form>").append("</form>").append(
                "<p><a href=").append(req.getContextPath()).append("/create>Add new user</a></p>").append(
                "</body>\n").append("</html>");
        writer.flush();

    }

    @Override
    public void destroy() {
        UserStore.getBase().shutDownDataSource();
    }
}
