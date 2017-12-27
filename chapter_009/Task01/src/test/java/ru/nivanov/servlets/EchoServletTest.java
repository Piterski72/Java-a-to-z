package ru.nivanov.servlets;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Nikolay Ivanov on 19.12.2017.
 */
public class EchoServletTest {

    @Test
    public void showEcho() throws Exception {

        EchoServlet servlet = new EchoServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        StringWriter stream = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stream);

        when(response.getWriter()).thenReturn(printWriter);

        servlet.doGet(request, response);

        assertThat(stream.toString(), is("hello world"));


    }

}