package ru.nivanov.netFileManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Server acting manager.
 * @author nivanov.
 */
class ServerManager {
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private final Settings settings;
    private final DataInputStream dis;
    private final DataOutputStream dos;
    private final String sep = System.getProperty("line.separator");
    private final BaseAction[] actions = new BaseAction[FIVE + 1];
    private String currentDir;

    ServerManager(Settings settings, DataInputStream dis, DataOutputStream dos) {
        this.settings = settings;
        this.dis = dis;
        this.dos = dos;
    }

    /**
     * Get current directory.
     * @return current directory
     */
    public String getCurrentDir() {
        return this.currentDir;
    }

    /**
     * Set current directory.
     * @param currentDir ..
     */
    private void setCurrentDir(String currentDir) {
        this.currentDir = currentDir;
    }

    /**
     * View directory.
     * @param directory ..
     * @param out ..
     * @throws IOException ..
     */
    private void viewDir(File directory, DataOutputStream out) throws IOException {
        for (File sub : directory.listFiles()) {
            out.writeUTF(String.format("%s%s", sub, sep));
        }
    }

    /**
     * Build menu realization.
     */
    void fillActions() {
        actions[0] = this.new MainDirShow();
        actions[1] = this.new SubDirShow();
        actions[2] = this.new ParentDirShow();
        actions[THREE] = this.new UploadFile();
        actions[FOUR] = this.new DownloadFile();
        actions[FIVE] = this.new ExitProgramm();
        setCurrentDir(settings.getValue("serverDir"));
    }

    /**
     * Select menu realization.
     * @param key ..
     * @throws IOException ..
     */
    void select(int key) throws IOException {
        try {
            this.actions[key].execute();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            System.out.println("Incorrect data, please try again");
        }
    }

    /**
     * Show menu realization.
     * @throws IOException ..
     */
    void show() throws IOException {
        for (BaseAction action : this.actions) {
            if (action != null) {
                this.dos.writeUTF(String.format("--  %s %s", action.key(), sep));
            }
        }
        this.dos.writeUTF("input menu number: ");
        //this.dis.readUTF();
    }

    /**
     * Get action (for testing purposes only).
     * @param key ..
     * @return actions ..
     */
    public BaseAction getAction(int key) {
        return actions[key];
    }

    /**
     * MainDirShow inner class.
     * @author nivanov.
     */
    public class MainDirShow implements BaseAction {
        /**
         * key method.
         * @return key..
         */
        public String key() {
            return "0. Show main server directory";
        }

        /**
         * Executing main action.
         * @throws IOException ..
         */
        public void execute() throws IOException {
            setCurrentDir(settings.getValue("serverDir"));
            File file = new File(getCurrentDir());
            dos.writeUTF(String.format("Current dir is %s%s", getCurrentDir(), sep));
            viewDir(file, dos);
        }
    }

    /**
     * SubDirShow inner class.
     * @author nivanov.
     */
    public class SubDirShow implements BaseAction {
        /**
         * key method.
         * @return key..
         */
        public String key() {
            return "1. Show server sub directory";
        }

        /**
         * Executing main action.
         * @throws IOException ..
         */
        public void execute() throws IOException {
            dos.writeUTF(String.format("Enter subdir name %s", sep));
            String subDir = dis.readUTF();
            String underDir = getCurrentDir() + "\\" + subDir;
            File file = new File(underDir);
            if (file.exists() & file.isDirectory()) {
                setCurrentDir(underDir);
                viewDir(file, dos);
            } else {
                dos.writeUTF("invalid directory");
            }
        }
    }

    /**
     * ParentDirShow inner class.
     * @author nivanov.
     */
    public class ParentDirShow implements BaseAction {
        /**
         * key method.
         * @return key..
         */
        public String key() {
            return "2. Show parent server directory";
        }

        /**
         * Executing main action.
         * @throws IOException ..
         */
        public void execute() throws IOException {
            if (!getCurrentDir().equals(settings.getValue("serverDir"))) {
                File file = new File(getCurrentDir());
                setCurrentDir(file.getParent());
                File parent = new File(getCurrentDir());
                dos.writeUTF(String.format("Current dir is %s%s", getCurrentDir(), sep));
                viewDir(parent, dos);
            } else {
                dos.writeUTF(String.format("Main dir reached, current dir is %s%s", getCurrentDir(), sep));
                viewDir(new File(getCurrentDir()), dos);
            }
        }
    }

    /**
     * UploadFile inner class.
     * @author nivanov.
     */
    public class UploadFile implements BaseAction {
        /**
         * key method.
         * @return key..
         */
        public String key() {
            return "3. Upload to server file operation";
        }

        /**
         * Executing main action.
         * @throws IOException ..
         */
        public void execute() throws IOException {
            dos.writeUTF(String.format("Enter file name for uploading %s", sep));
            String upfile = dis.readUTF();
            if (!upfile.equals("file not found")) {
                File file = new File(getCurrentDir(), upfile);
                int fileLength = dis.readInt();
                FileTransfer transfer = new FileTransfer();
                transfer.streamToFile(dis, file, fileLength);
                dos.writeUTF(String.format("file %s uploaded to srv", file.getName()));
                viewDir(new File(getCurrentDir()), dos);
                System.out.println(String.format("file %s uploaded to srv", file.getName()));
            }
        }
    }

    /**
     * DownloadFile inner class.
     * @author nivanov.
     */
    public class DownloadFile implements BaseAction {
        /**
         * key method.
         * @return key..
         */
        public String key() {
            return "4. Download from server file operation";
        }

        /**
         * Executing main action.
         * @throws IOException ..
         */
        public void execute() throws IOException {
            dos.writeUTF(String.format("Enter file name for downloading %s", sep));
            String dlfile = dis.readUTF();
            File file = new File(getCurrentDir(), dlfile);
            if (file.exists() & file.isFile()) {
                dos.writeUTF("file exists");
                dos.writeInt((int) file.length());
                FileTransfer transfer = new FileTransfer();
                transfer.fileToStream(file, dos);
                String str = dis.readUTF();
                System.out.printf(String.format("file %s %s ", file.getName(), str));
            } else {
                dos.writeUTF("error");
            }
        }
    }

    /**
     * ExitProgramm inner class.
     * @author nivanov.
     */
    public class ExitProgramm implements BaseAction {
        /**
         * key method.
         * @return key..
         */
        public String key() {
            return "5. Exit programm.";
        }

        /**
         * Executing main action.
         * @throws IOException ..
         */
        public void execute() throws IOException {
            dos.writeUTF(String.format("Exitting... %s ", sep));
        }
    }
}