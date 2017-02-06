package ru.nivanov.botOracle;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pit on 06.02.2017.
 */
public class BotOracleServerTest {
   private String sep = System.getProperty("line separator");

    /**
     * test for server 1.
     * @throws Exception ..
     */
    @Test
    public void whenAskAnswerThenChooseRandom() throws Exception {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        BotOracleServer underTest = new BotOracleServer(socket);
        underTest.start();
        assertThat(out.toString(), is(""));
    }
    /**
     * test for server 2.
     * @throws Exception ..
     */
    @Test
    public void whenAskHelloThenChooseGreeting() throws Exception {
        Socket socket = mock(Socket.class);
        String one = "Hello oracle";
        String two = "exit";
        ByteArrayInputStream in = new ByteArrayInputStream((String.format("%s%s%s", one, "\r\n", two)).getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        BotOracleServer underTest = new BotOracleServer(socket);
        underTest.start();
        assertThat(out.toString(), is(String.format("%s%s%s", "Hello, dear friend, I'm an oracle.", "\r\n", "\r\n")));
    }
    /**
     * test for server 2.
     * @throws Exception ..
     */
    @Test
    public void whenAskAnswerThenChooseRandom2() throws Exception {
        Socket socket = mock(Socket.class);
        String one = "Hello";
        String two = "exit";
        ByteArrayInputStream in = new ByteArrayInputStream((String.format("%s%s%s", one, "\r\n", two)).getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        BotOracleServer underTest = new BotOracleServer(socket);
        underTest.start();
        assertNotNull(out.toString());
    }
}