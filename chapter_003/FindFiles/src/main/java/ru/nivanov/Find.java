package ru.nivanov;

import com.google.common.base.Joiner;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 08.02.2017.
 */
public class Find {
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static String[] keysAndValues = new String[SEVEN];
    private static ArrayList<String> result = new ArrayList<>();
    private static String sep = System.getProperty("line.separator");
    private File log;
    private Settings settings = new Settings();

    /**
     * main method receive args
     * @param args ..
     * @throws IOException ..
     */
    public static void main(String[] args) throws IOException {
        showTips();
        Find finder = new Find();
        finder.setUp(args[SIX]);
        System.out.println(System.getProperty("user.dir"));
        if (args.length == 0 | args.length != SEVEN) {
            System.out.println("Use help for keys");
        } else {
            System.arraycopy(args, 0, keysAndValues, 0, args.length);
            if (finder.validate(keysAndValues)) {
                finder.search2(keysAndValues[1], keysAndValues[THREE]);
                finder.writeToLog(result);
            }
        }
    }

    /**
     * show help
     */
    private static void showTips() {
        System.out.println(Joiner.on(sep).join("Usage: find.jar -d dirname -n filename -args -o logfile",
                "where keys and values are:", "dirname is directory name, i.e. c:/",
                "filename is full file name or mask (i.e. .txt) or part of file name",
                "-args may be -m (find by mask) or -f (find exact name) or -r (find by partial coincidence)",
                "logfile is the name of log file with resuls, stored in c:/findLogs.logFileName"));
    }

    /**
     * validating keys
     * @param input comes from main method
     * @return validate result
     */
    private boolean validate(String[] input) {
        boolean validation = false;
        int countZeroArg = 0;
        for (String str : input) {
            if (str.equals("")) {
                countZeroArg++;
            }
        }
        if (countZeroArg == 0) {
            String expected1 = "-d-n-o";
            String expected2 = "-m-f-r";
            if (!Joiner.on("").join(input[0], input[2], input[FIVE]).equals(expected1)) {
                System.out.println("incorrect key -d -n -o usage, quitting...");
            } else if (!expected2.contains(input[FOUR])) {
                System.out.println("incorrect key -m -f -r usage, quitting...");
            } else {
                validation = true;
            }
        }
        return validation;
    }

    /**
     * search engine uses walkFileTree
     * @param dirName is starting directory
     * @param name is search object
     * @throws IOException ..
     */
    private void search2(String dirName, String name) throws IOException {
        File test = new File(dirName);
        if (test.exists() & test.isDirectory()) {
            Files.walkFileTree(Paths.get(dirName), new MyFileVisitor(name));
        } else {
            System.out.println("No such directory");
        }
    }

    /**
     * setting up default directiry for log file
     * @param logName is log file name
     */
    private void setUp(String logName) {
        ClassLoader loader = Settings.class.getClassLoader();
        InputStream io = loader.getResourceAsStream("app.properties");
        this.settings.load(io);
        String currentLogDir = this.settings.getValue("defaultLogDir");
        File logDir = new File(currentLogDir);
        logDir.mkdir();
        log = new File(currentLogDir, logName);
    }

    /**
     * writing search result to log file
     * @param input is array of found paths
     * @throws IOException ..
     */
    private void writeToLog(ArrayList<String> input) throws IOException {
        try (BufferedWriter bwr = new BufferedWriter(new FileWriter(log))) {
            for (String anInput : input) {
                bwr.write(String.format("%s%s", anInput, sep));
            }
        }
    }

    /**
     * Class MyFileVisitor with methods for visit directories and files and getting visit results
     */
    class MyFileVisitor extends SimpleFileVisitor<Path> {
        String name;

        MyFileVisitor(String name) {
            this.name = name;
        }

        /**
         * method for visiting file
         * @param path is searching path
         * @param attribs is file basic attributes
         * @return result of visiting files
         * @throws IOException ..
         */
        public FileVisitResult visitFile(Path path, BasicFileAttributes attribs) throws IOException {
            if (attribs.isRegularFile() & !(new File(path.toString())).isHidden()) {
                switch (keysAndValues[4]) {
                    case "-m":
                        if (name.matches("\\*\\..+")) {
                            String mask = name.substring(1);
                            File foundByMask = new File(path.toString());
                            String expect = foundByMask.toString();
                            if (foundByMask.isFile() & expect.substring(expect.length() - mask.length()).equals(mask)) {
                                System.out.println(foundByMask.toString());
                                result.add(foundByMask.toString());
                            }
                        }
                        break;
                    case "-f":
                        File foundExact = new File(path.toString());
                        if (foundExact.isFile() & foundExact.getName().equals(name)) {
                            System.out.println(foundExact.toString());
                            result.add(foundExact.toString());
                        }
                        break;
                    case "-r":
                        File foundPart = new File(path.toString());
                        if (foundPart.isFile() & foundPart.getName().contains(name)) {
                            System.out.println(foundPart.toString());
                            result.add(foundPart.toString());
                        }
                        break;
                }
            }
            return FileVisitResult.CONTINUE;
        }

        /**
         * method works before visiting directory
         * @param path is searching path
         * @param attribs is basic file attributes
         * @return resut
         * @throws IOException ..
         */
        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attribs) throws IOException {
            if (attribs.isDirectory() & !attribs.isOther()) {
                System.out.println(path);
            }
            return FileVisitResult.CONTINUE;
        }

        /**
         * method works when visit failed
         * @param path is searching path
         * @param ioe is exception object
         * @return result
         * @throws IOException ..
         */
        public FileVisitResult visitFileFailed(Path path, IOException ioe) throws IOException {
            System.err.printf("Visiting failed for %s\n", path);
            return FileVisitResult.SKIP_SUBTREE;
        }
    }
}

