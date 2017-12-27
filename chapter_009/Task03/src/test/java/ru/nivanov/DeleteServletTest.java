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
public class DeleteServletTest {

    @Test
    public void whenDoPostThenDeleteUser() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        Map<Integer, User> userMap = UserStore.getBase().showUsers();
        Set<Integer> idSet = userMap.keySet();

        if (!idSet.isEmpty()) {

            int id = idSet.iterator().next();
            String idkey = Integer.toString(id);

            when(request.getParameter("id")).thenReturn(idkey);

            StringWriter stream = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stream);

            when(response.getWriter()).thenReturn(printWriter);

            new DeleteServlet().doPost(request, response);

            Map<Integer, User> userMapUpdated = UserStore.getBase().showUsers();

            boolean result = userMapUpdated.containsKey(Integer.parseInt(idkey));

            assertThat(result, is(false));

        } else {
            throw new NullPointerException("no records exist");
        }

    }

}