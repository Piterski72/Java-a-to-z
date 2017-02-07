package ru.nivanov.botOracle;

import com.google.common.base.Joiner;
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
 * Created by Nikolay Ivanov on 06.02.2017.
 */
public class BotOracleServerTest {
    private String sep = System.getProperty("line.separator");

    /**
     * test for server 1.
     * @throws Exception ..
     */
    @Test
    public void whenAskAnswerThenChooseRandom() throws Exception {
        this.testOracleServer("exit", "");
    }

    /**
     * test for server 2.
     * @throws Exception ..
     */
    @Test
    public void whenAskHelloThenChooseGreeting() throws Exception {
        this.testOracleServer(Joiner.on(sep).join("Hello oracle", "exit"),
                Joiner.on(sep).join("Hello, dear friend, I'm an oracle.", sep));
    }

    /**
     * test for server 3.
     * @throws Exception ..
     */
    @Test
    public void whenAskAnswerThenChooseRandom2() throws Exception {
        Socket socket = mock(Socket.class);
        String one = "Hello";
        String two = "exit";
        ByteArrayInputStream in = new ByteArrayInputStream((String.format("%s%s%s", one, sep, two)).getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        BotOracleServer underTest = new BotOracleServer(socket);
        underTest.start();
        assertNotNull(out.toString());
    }

    private void testOracleServer(String input, String expected) throws Exception {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        BotOracleServer underTest = new BotOracleServer(socket);
        underTest.start();
        assertThat(out.toString(), is(expected));
    }
}