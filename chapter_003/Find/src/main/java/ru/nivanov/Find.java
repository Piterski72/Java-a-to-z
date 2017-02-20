package ru.nivanov;

import com.google.common.base.Joiner;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 08.02.2017.
 */
class Find {
    private static final int SEVEN = 7;
    private static final String sep = System.getProperty("line.separator");
    private static Param param;
    private final Settings settings = new Settings();
    private File log;

    /**
     * main method receive args.
     * @param args ..
     * @throws IOException ..
     */
    public static void main(String[] args) throws IOException {
        showTips();
        param = new Param(args);
        Find finder = new Find();
        finder.setUp(param.getLogName());
        System.out.println(System.getProperty("user.dir"));
        if (args.length == 0 | args.length != SEVEN) {
            System.out.println("Use help for keys");
        } else {
            if (finder.validate(param)) {
                finder.search2(param);
            }
        }
    }

    /**
     * show help.
     */
    static void showTips() {
        System.out.println(Joiner.on(sep).join("Usage: find.jar -d dirname -n filename -args -o logfile",
                "where keys and values are:", "dirname is directory name, i.e. c:/",
                "filename is full file name or mask (i.e. .txt) or part of file name",
                "-args may be -m (find by mask) or -f (find exact name) or -r (find by partial coincidence)",
                "logfile is the name of log file with resuls, stored in c:/findLogs.logFileName"));
    }

    /**
     * validating keys.
     * @param param comes from main method
     * @return validate result
     */
    boolean validate(Param param) {
        boolean validation = false;
        String expected1 = "-d-n-o";
        String expected2 = "-m-f-r";
        if (!Joiner.on("").join(param.getDparam(), param.getNparam(), param.getOparam()).equals(expected1)) {
            System.out.println("incorrect key -d -n -o usage, quitting...");
        } else if (!expected2.contains(param.getMFRparam())) {
            System.out.println("incorrect key -m -f -r usage, quitting...");
        } else {
            validation = true;
        }

        return validation;
    }

    /**
     * search engine uses walkFileTree.
     * @param param ..
     * @throws IOException ..
     */
    private void search2(Param param) throws IOException {
        File test = new File(param.getDirName());
        if (test.exists() & test.isDirectory()) {
            Files.walkFileTree(Paths.get(param.getDirName()), new MyFileVisitor(param.getFileName()));
        } else {
            System.out.println("No such directory");
        }
    }

    /**
     * setting up default directiry for log file.
     * @param logName is log file name
     */
    private void setUp(String logName) {
        ClassLoader loader = Settings.class.getClassLoader();
        InputStream io = loader.getResourceAsStream("app.properties");
        this.settings.load(io);
        String currentLogDir = this.settings.getValue();
        File logDir = new File(currentLogDir);
        logDir.mkdir();
        log = new File(currentLogDir, logName);
    }

    /**
     * writing search result to log file.
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
     * Class MyFileVisitor with methods for visit directories and files and getting visit results.
     */
    class MyFileVisitor extends SimpleFileVisitor<Path> {
        private final String name;

        MyFileVisitor(String name) {
            this.name = name;
        }

        /**
         * method for visiting file.
         * @param path is searching path
         * @param attribs is file basic attributes
         * @return result of visiting files
         * @throws IOException ..
         */
        public FileVisitResult visitFile(Path path, BasicFileAttributes attribs) throws IOException {
            SearchMode mode = new SearchMode();
            if (attribs.isRegularFile() & !(new File(path.toString())).isHidden()) {
                mode.fillActions();
                ArrayList<String> searchingResult = mode.modeSelect(param.getMFRparam(), name, path);
                writeToLog(searchingResult);
            }
            return FileVisitResult.CONTINUE;
        }

        /**
         * method works before visiting directory.
         * @param path is searching path.
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
         * method works when visit failed.
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

