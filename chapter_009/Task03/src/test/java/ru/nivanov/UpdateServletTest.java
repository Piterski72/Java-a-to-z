package ru.nivanov;

import org.junit.Test;
import ru.nivanov.model.User;
import ru.nivanov.model.UserStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Nikolay Ivanov on 25.12.2017.
 */
public class UpdateServletTest {
    @Test
    public void whenUpdateUserThenReturnResult() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        StringWriter stream = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stream);

        when(response.getWriter()).thenReturn(printWriter);

        when(request.getParameter("login")).thenReturn("testlog2");
        when(request.getParameter("email")).thenReturn("testmail2");
        when(request.getParameter("name")).thenReturn("testname2");

        Map<Integer, User> userMap = UserStore.getBase().showUsers();
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

        new UpdateServlet().doPost(request, response);

        Map<Integer, User> userMapUpdated = UserStore.getBase().showUsers();
        User current = null;
        int userID = Integer.parseInt(id);

        if (userMapUpdated.containsKey(userID)) {
            current = userMap.get(userID);
        }
        assertThat(current.getLogin(), is(login));
        assertThat(current.getEmail(), is(email));
        assertThat(current.getName(), is(name));
    }

}