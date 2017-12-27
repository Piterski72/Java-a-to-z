package ru.nivanov;

import org.junit.Test;
import ru.nivanov.model.User;
import ru.nivanov.model.UserStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Nikolay Ivanov on 21.12.2017.
 */
public class CreateServletTest {
    @Test
    public void whenAddNewUserThenReturnResult() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter stream = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stream);

        when(response.getWriter()).thenReturn(printWriter);

        when(request.getParameter("login")).thenReturn("testlogin");
        when(request.getParameter("name")).thenReturn("testname");
        when(request.getParameter("email")).thenReturn("testmail");

        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String name = request.getParameter("name");

        assertThat(login, is("testlogin"));
        assertThat(email, is("testmail"));
        assertThat(name, is("testname"));

        new CreateServlet().doPost(request, response);

        Map<Integer, User> userMap = UserStore.getBase().showUsers();
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

}