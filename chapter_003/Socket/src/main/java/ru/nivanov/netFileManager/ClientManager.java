package ru.nivanov.netFileManager;

import java.io.*;

/**
 * Client acting manager.
 * @author nivanov.
 */
class ClientManager {
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private final Settings settings;
    private final DataInputStream dis;
    private final DataOutputStream dos;
    private final BufferedReader reader;
    private final BaseAction[] actions = new BaseAction[FIVE + 1];
    private String currentClientDir;

    ClientManager(Settings settings, DataInputStream dis, DataOutputStream dos, BufferedReader reader) {
        this.settings = settings;
        this.dis = dis;
        this.dos = dos;
        this.reader = reader;
    }

    /**
     * Get current client directory.
     * @return current directory
     */
    private String getCurrentClientDir() {
        return this.currentClientDir;
    }

    /**
     * Set current client directory.
     */
    private void setCurrentClientDir() {
        currentClientDir = this.settings.getValue("clientDir");
    }

    /**
     * View directory.
     */
    private void viewClientDir() {
        System.out.println(String.format("current client directory:  %s", getCurrentClientDir()));
        File file = new File(getCurrentClientDir());
        for (File sub : file.listFiles()) {
            System.out.println(String.format("%s", sub));
        }
    }

    /**
     * Client reactions.
     */
    void clientActions() {
        actions[0] = this.new TextDialog();
        actions[1] = this.new SubDir();
        actions[2] = this.new TextDialog();
        actions[THREE] = this.new FileOutDialog();
        actions[FOUR] = this.new FileInDialog();
        actions[FIVE] = this.new TextDialog();
        setCurrentClientDir();
    }

    /**
     * Get server menu.
     * ..
     * @throws IOException ..
     */
    void getSrvMenu() throws IOException {
        String str;
        do {
            str = this.dis.readUTF();
            System.out.println(str);
        } while (!str.equals("input menu number: "));
    }

    /**
     * Select client reaction.
     * @param key ..
     * @throws IOException ..
     */
    void select(int key) throws IOException {
        if (key < 0 | key > 5) {
            System.out.println("out of menu range");
        }
        this.actions[key].execute();
    }

    /**
     * TextDialog inner class.
     * @author nivanov.
     */
    private class TextDialog implements BaseAction {
        /**
         * key method.
         * @return key..
         */
        public String key() {
            return "0";
        }

        /**
         * Executing main action.
         * @throws IOException ..
         */
        public void execute() throws IOException {
            String str = dis.readUTF();
            System.out.println(str);
        }
    }

    /**
     * SubDir inner class.
     * @author nivanov.
     * @version 1.0
     * @since 31.01.2017
     */
    private class SubDir implements BaseAction {
        /**
         * key method.
         * @return key..
         */
        public String key() {
            return "1";
        }

        /**
         * Executing main action.
         * @throws IOException ..
         */
        public void execute() throws IOException {
            String str = dis.readUTF();
            System.out.println(str);
            String dirName = reader.readLine();
            dos.writeUTF(dirName);
            str = dis.readUTF();
            System.out.println(str);
        }
    }

    /**
     * FileOutDialog inner class.
     * @author nivanov.
     */
    private class FileOutDialog implements BaseAction {
        /**
         * key method.
         * @return key..
         */
        public String key() {
            return "3";
        }

        /**
         * Executing main action.
         * @throws IOException ..
         */
        public void execute() throws IOException {
            viewClientDir();
            String str = dis.readUTF();
            System.out.println(str);
            String fileName = reader.readLine();
            File file = new File(getCurrentClientDir(), fileName);
            if (file.exists() & file.isFile()) {
                dos.writeUTF(fileName);
                dos.writeInt((int) file.length());
                FileTransfer transfer = new FileTransfer();
                transfer.fileToStream(file, dos);
                str = dis.readUTF();
                System.out.println(str);
            } else {
                System.out.println("file not found");
                dos.writeUTF("file not found");
            }
        }
    }

    /**
     * FileInDialog inner class.
     * @author nivanov.
     */
    private class FileInDialog implements BaseAction {
        /**
         * key method.
         * @return key..
         */
        public String key() {
            return "4";
        }

        /**
         * Executing main action.
         * @throws IOException ..
         */
        public void execute() throws IOException {
            String str = dis.readUTF();
            System.out.println(str);
            String fileName = reader.readLine();
            dos.writeUTF(fileName);
            if (dis.readUTF().equals("file exists")) {
                File file = new File(getCurrentClientDir(), fileName);
                int fileLength = dis.readInt();
                FileTransfer transfer = new FileTransfer();
                transfer.streamToFile(dis, file, fileLength);
                dos.writeUTF("download ok");
                viewClientDir();
                System.out.println("download ok");
            } else {
                System.out.println("file not found");
            }
        }
    }
}