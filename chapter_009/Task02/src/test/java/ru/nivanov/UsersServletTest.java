package ru.nivanov;

import org.junit.Before;
import org.junit.Test;
import ru.nivanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Nikolay Ivanov on 20.12.2017.
 */
public class UsersServletTest {

    HttpServletRequest request;
    HttpServletResponse response;
    UsersServlet usersServlet = new UsersServlet();

    @Before
    public void setUp() throws Exception {

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

    }

    @Test
    public void whenDoGetThenGetList() throws Exception {

        StringWriter stream = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stream);
        when(response.getWriter()).thenReturn(printWriter);
        usersServlet.doGet(request, response);

        assertTrue(stream.toString().trim().contains("id") && stream.toString().trim().contains(
                "login") && stream.toString().trim().contains("name"));

    }

    @Test
    public void whenDoPostThenAddUser() throws NullPointerException, IOException, ServletException {

        when(request.getParameter("login")).thenReturn("testlog");
        when(request.getParameter("email")).thenReturn("testmail");
        when(request.getParameter("name")).thenReturn("testname");

        usersServlet.doPost(request, response);

        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String name = request.getParameter("name");

        assertThat(login, is("testlog"));
        assertThat(email, is("testmail"));
        assertThat(name, is("testname"));

        Map<Integer, User> userMap = DbaseHandler.getBase().showUsers();
        boolean userFound = false;
        for (Map.Entry<Integer, User> items : userMap.entrySet()) {
            User current = items.getValue();
            if (current.getName().equals(name) && current.getEmail().equals(email) && current.getLogin().equals(
                    login)) {
                userFound = true;
                break;
            }
        }
        assertThat(userFound, is(true));
    }

    @Test
    public void whenDoPutThenUpdateUser() throws Exception {

        when(request.getParameter("login")).thenReturn("testlog2");
        when(request.getParameter("email")).thenReturn("testmail2");
        when(request.getParameter("name")).thenReturn("testname2");

        Map<Integer, User> userMap = DbaseHandler.getBase().showUsers();
        Set<Integer> idSet = userMap.keySet();
        String idKey = idSet.iterator().next().toString();

        when(request.getParameter("id")).thenReturn(idKey);

        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String id = request.getParameter("id");

        assertThat(login, is("testlog2"));
        assertThat(email, is("testmail2"));
        assertThat(name, is("testname2"));
        assertThat(id, is(idKey));

        usersServlet.doPut(request, response);

        Map<Integer, User> userMapUpdated = DbaseHandler.getBase().showUsers();
        User current = null;
        int userID = Integer.parseInt(id);

        if (userMapUpdated.containsKey(userID)) {
            current = userMap.get(userID);
        }
        assertThat(current.getLogin(), is(login));
        assertThat(current.getEmail(), is(email));
        assertThat(current.getName(), is(name));

    }

    @Test
    public void whenDoDeleteThenDeleteUser() throws Exception {

        Map<Integer, User> userMap = DbaseHandler.getBase().showUsers();
        Set<Integer> idSet = userMap.keySet();

        if (!idSet.isEmpty()) {

            int id = idSet.iterator().next();
            String idkey = Integer.toString(id);

            when(request.getParameter("id")).thenReturn(idkey);

            usersServlet.doDelete(request, response);

            Map<Integer, User> userMapUpdated = DbaseHandler.getBase().showUsers();

            boolean result = userMapUpdated.containsKey(Integer.parseInt(idkey));

            assertThat(result, is(false));

        } else {
            throw new NullPointerException("no records exist");
        }


    }

}