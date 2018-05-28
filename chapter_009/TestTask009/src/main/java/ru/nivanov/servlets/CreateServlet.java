package ru.nivanov.servlets;

import org.json.simple.JSONObject;
import ru.nivanov.baseutils.PostgresBaseUtils;
import ru.nivanov.repository.UserRepository;
import ru.nivanov.repository.implrep.UserRepoImpl;

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

        String rolename = req.getParameter("rolename");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String rawMusic = req.getParameter("music");

        String rawCleaned = rawMusic.replaceAll("&nbsp;", "");
        String premusic = rawCleaned.replaceAll("[\\s]*,[\\s]*", ",");

        String[] musics = premusic.split(",");

        UserRepository userRepository = new UserRepoImpl();
        boolean result = userRepository.saveEntity(name, address, rolename, musics);

        JSONObject object = new JSONObject();
        if (result) {
            object.put("result", "create ok!");
        } else {
            object.put("result", "create failed!");
        }

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(object);
        out.flush();
    }

    @Override
    public void destroy() {
        PostgresBaseUtils.getBase().shutDownDataSource();
    }
}
