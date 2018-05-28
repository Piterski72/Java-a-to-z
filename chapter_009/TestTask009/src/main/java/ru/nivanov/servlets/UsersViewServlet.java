package ru.nivanov.servlets;

import com.google.gson.Gson;
import ru.nivanov.baseutils.PostgresBaseUtils;
import ru.nivanov.entity.Address;
import ru.nivanov.entity.MusicType;
import ru.nivanov.entity.Role;
import ru.nivanov.repository.UserRepository;
import ru.nivanov.repository.implrep.UserRepoImpl;
import ru.nivanov.repository.mapper.Mapper;
import ru.nivanov.repository.mapper.StringToAddressMapper;
import ru.nivanov.repository.mapper.StringToMusicMapper;
import ru.nivanov.repository.mapper.StringToRoleMapper;
import ru.nivanov.repository.repoentity.UserResult;
import ru.nivanov.repository.specification.UserByAddressSpec;
import ru.nivanov.repository.specification.UserByMusicSpec;
import ru.nivanov.repository.specification.UserByRoleSpec;
import ru.nivanov.repository.specification.UserGetAll;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Nikolay Ivanov on 24.10.2017.
 */
public class UsersViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserRepository userRepository = new UserRepoImpl();
        List<UserResult> userResults = userRepository.query(new UserGetAll());
        HttpSession session = req.getSession(false);
        Gson gson = new Gson();
        String json = "";
        String rolename = (String) session.getAttribute("role");
        if (rolename != null) {
            json = gson.toJson(this.details(userResults));
        }
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.println(json);
        writer.flush();
    }

    /**
     * Get details for json.
     * @param results ..
     * @return ..
     */
    private List<List<String>> details(List<UserResult> results) {
        List<List<String>> details = new CopyOnWriteArrayList<>();
        for (UserResult userResult : results) {
            List<String> record = new CopyOnWriteArrayList<>();
            record.add(String.valueOf(userResult.getUser().getId()));
            record.add(userResult.getUser().getName());
            record.add(userResult.getAddress().getLocation());
            record.add(userResult.getRole().getRolename());
            record.add(musucSetToString(userResult.getTypes()));
            details.add(record);
        }
        return details;
    }

    /**
     * Converts music set to one string.
     * @param types ..
     * @return ..
     */
    private String musucSetToString(Set<MusicType> types) {
        StringJoiner sj = new StringJoiner(",");
        for (MusicType mus : types) {
            sj.add(mus.getType());
        }
        return sj.toString();
    }

    @Override
    public void destroy() {
        PostgresBaseUtils.getBase().shutDownDataSource();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address = req.getParameter("address");
        String rolename = req.getParameter("rolename");
        String music = req.getParameter("music");

        UserRepository userRepository = new UserRepoImpl();
        List<List<String>> details = new CopyOnWriteArrayList<>();

        if (!address.isEmpty()) {
            Mapper<String, Address> mapper = new StringToAddressMapper();
            Address addr = mapper.map(address);
            if (!addr.getLocation().equals("empty")) {
                List<UserResult> users = userRepository.query(new UserByAddressSpec(addr));
                details = this.details(users);
            }
        } else if (!rolename.isEmpty()) {
            Mapper<String, Role> mapper = new StringToRoleMapper();
            Role role = mapper.map(rolename);
            if (!role.getRolename().equals("empty")) {
                List<UserResult> users = userRepository.query(new UserByRoleSpec(role));
                details = this.details(users);
            }
        } else if (!music.isEmpty()) {
            Mapper<String, MusicType> mapper = new StringToMusicMapper();
            MusicType musicType = mapper.map(music);
            if (!musicType.getType().equals("empty")) {
                List<UserResult> users = userRepository.query(new UserByMusicSpec(musicType));
                details = this.details(users);
            }
        }
        HttpSession session = req.getSession(false);
        Gson gson = new Gson();
        String json = "";
        String userrole = (String) session.getAttribute("role");
        if (userrole != null) {
            json = gson.toJson(details);
        }
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.println(json);
        writer.flush();

    }
}
