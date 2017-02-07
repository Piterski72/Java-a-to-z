package ru.nivanov.botOracle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by pit on 06.02.2017.
 */
public class BotOracleServer {
    private static final int PORT = 40000;
    private final Settings settings = new Settings();
    private Socket socket;

    public BotOracleServer(Socket socket) {
        this.socket = socket;
    }

    /**
     * main method.
     * @param args input param
     * @throws IOException ..
     */
    public static void main(String[] args) throws IOException {
        try (final Socket socket = new ServerSocket(PORT).accept()) {
            new BotOracleServer(socket).start();

        }
    }

    /**
     * Starting server.
     * @throws IOException ..
     */
    public void start() throws IOException {
        setUp();
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            String ask;
            String randomAnswer;
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("Hello oracle".equals(ask)) {
                    out.println("Hello, dear friend, I'm an oracle.");
                    out.println();
                } else if (!"exit".equals(ask)) {
                    randomAnswer = generateAnswer();
                    out.println(randomAnswer);
                    out.println();
                }
            } while (!"exit".equals(ask));

        }

    }


    /**
     * generating bot random answer.
     * @return resulst getting bot random answer ..
     * @throws IOException ..
     */
    private String generateAnswer() throws IOException {
        int j = lineNumber();
        int i = 0;
        String result = "";
        Random rnd = new Random();
        int rndNumber = rnd.nextInt(j);
        File answers = new File(this.settings.getValue("botAnswerFile"));
        try (RandomAccessFile raf = new RandomAccessFile(answers, "r")) {
            while (raf.getFilePointer() != raf.length()) {
                result = raf.readLine();
                i++;
                if ((i - 1) == rndNumber) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * counting number of string lines in file.
     * @return count ..
     * @throws IOException ..
     */
    private int lineNumber() throws IOException {
        int count = 0;
        File answers = new File(this.settings.getValue("botAnswerFile"));
        try (RandomAccessFile raf = new RandomAccessFile(answers, "r")) {
            while (raf.getFilePointer() != raf.length()) {
                raf.readLine();
                count++;
            }
        }
        return count;
    }

    /**
     * Loading apps properties.
     */
    public void setUp() {
        ClassLoader loader = Settings.class.getClassLoader();
        InputStream io = loader.getResourceAsStream("ru/nivanov/botOracle/app.properties");
        this.settings.load(io);
    }
}