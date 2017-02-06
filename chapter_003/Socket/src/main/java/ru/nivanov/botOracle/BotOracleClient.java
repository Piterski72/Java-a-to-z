package ru.nivanov.botOracle;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by pit on 06.02.2017.
 */
public class BotOracleClient {
    private final Settings settings = new Settings();

    /**
     * main method.
     * @param args input param
     * @throws IOException ..
     */
    public static void main(String[] args) throws IOException {
        new BotOracleClient().start();
    }

    /**
     * Loading apps properties.
     */
    private void setUp() {
        ClassLoader loader = Settings.class.getClassLoader();
        InputStream io = loader.getResourceAsStream("ru/nivanov/botOracle/app.properties");
        this.settings.load(io);
    }

    /**
     * Starting client.
     * @throws IOException ..
     */
    public void start() throws IOException {
        setUp();
        int port = Integer.valueOf(this.settings.getValue("port"));
        String servAddress = this.settings.getValue("serverIp");
        Socket socket = new Socket(InetAddress.getByName(servAddress), port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        try (Scanner console = new Scanner(System.in)) {
            out.println("Hello oracle");
            String str = "";
            do {
                while (!(str = input.readLine()).isEmpty()) {
                    System.out.println(str);
                }
                str = console.nextLine();
                out.println(str);
            } while (!str.equals("exit"));
        }
    }
}


