package ru.nivanov.netFileManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server realization.
 * @author nivanov.
 */
class NFServer {
    private static final int FIVE = 5;
    private final Settings settings = new Settings();

    /**
     * main method.
     * @param args input param
     * @throws IOException ..
     */
    public static void main(String[] args) throws IOException {
        NFServer server = new NFServer();
        server.startServ();
    }

    /**
     * Loading apps properties.
     */
    private void setUp() {
        ClassLoader loader = Settings.class.getClassLoader();
        InputStream io = loader.getResourceAsStream("app.properties");
        this.settings.load(io);
    }

    /**
     * Starting server.
     * @throws IOException ..
     */
    private void startServ() throws IOException {
        setUp();
        int port = Integer.valueOf(this.settings.getValue("port"));
        try (ServerSocket income = new ServerSocket(port)) {
            System.out.println("waiting for connection...");
            try (Socket socket = income.accept()) {
                System.out.println("Connect ok!");
                try (InputStream inst = socket.getInputStream();
                     OutputStream outst = socket.getOutputStream();
                     DataInputStream dis = new DataInputStream(inst);
                     DataOutputStream dos = new DataOutputStream(outst)) {
                    ServerManager manager = new ServerManager(this.settings, dis, dos);
                    manager.fillActions();
                    int choice = 0;
                    while (FIVE != choice) {
                        try {
                            manager.show();
                            String str = dis.readUTF();
                            choice = Integer.valueOf(str);
                            manager.select(choice);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}