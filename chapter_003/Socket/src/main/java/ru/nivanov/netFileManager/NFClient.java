package ru.nivanov.netFileManager;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Client realization.
 * @author nivanov.
 */
public class NFClient {
    private final Settings settings = new Settings();
    private InputStream in;

    /**
     * main method.
     * @param args input param
     * @throws IOException ..
     */
    public static void main(String[] args) throws IOException {
        NFClient client = new NFClient();
        client.in = System.in;
        client.startClient();
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
     * Starting client.
     * @throws IOException ..
     */
    private void startClient() throws IOException {
        setUp();
        int servPort = Integer.valueOf(this.settings.getValue("port"));
        String servAddress = this.settings.getValue("serverIp");
        try {
            InetAddress address = InetAddress.getByName(servAddress);
            try (Socket socket = new Socket(address, servPort);
                 InputStream inst = socket.getInputStream();
                 OutputStream outst = socket.getOutputStream();
                 DataInputStream dis = new DataInputStream(inst);
                 DataOutputStream dos = new DataOutputStream(outst);
                 BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String str;
                System.out.println("Network file manager:");
                ClientManager clManager = new ClientManager(this.settings, dis, dos, br);
                clManager.clientActions();
                while (true) {
                    try {
                        clManager.getSrvMenu();
                        str = br.readLine();
                        dos.writeUTF(str);
                        clManager.select(Integer.valueOf(str));
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException ai) {
                        System.out.println("Please select key from menu (0 - 5): ");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}