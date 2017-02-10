package ru.nivanov;

import com.google.common.base.Joiner;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by Nikolay Ivanov on 08.02.2017.
 */
public class Find {
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static String[] keysAndValues = new String[SEVEN];
    private static String sep = System.getProperty("line.separator");
    public String currentDir;
    public String currentLogDir;
    public File log;
    public Settings settings = new Settings();
    // input

    public static void main(String[] args) throws IOException {
        showTips();
        Find finder = new Find();
        finder.setUp(args[SIX]);
        System.out.println(System.getProperty("user.dir"));
        if (args.length == 0 | args.length != SEVEN) {
            System.out.println("Use help for keys");
        } else {
            for (int i = 0; i < args.length; i++) {
                keysAndValues[i] = args[i];
            }
            if (finder.validate(keysAndValues)) {
                finder.search2(keysAndValues[1], keysAndValues[THREE]);
            }
        }
    }

    // executing

    public static void showTips() {
        System.out.println(Joiner.on(sep).join("Usage: find.jar -d dirname -n filename -args -o logfile",
                "where keys and values are:", "dirname is directory name, i.e. c:/",
                "filename is full file name or mask (i.e. .txt) or part of file name",
                "-args may be -m (find by mask) or -f (find exact name) or -r (find by partial coincidence)",
                "logfile is the name of log file with resuls, stored in c:/findLogs"));
    }

    public boolean validate(String[] input) {
        System.out.println("validating begins...");
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
                System.out.println("-d-n-o error");
                System.out.println("incorrect key usage, quitting...");
            } else if (!expected2.contains(input[FOUR])) {
                System.out.println("-m-f-r error");
                System.out.println(input[FOUR]);
                System.out.println("incorrect key usage, quitting...");
            } else {
                validation = true;
                System.out.println("validation ok!");
            }
        }
        return validation;
    }

    public void search2(String dirName, String name) throws IOException {
        try (BufferedWriter bwr = new BufferedWriter(new FileWriter(log))) {
            String result = (Files.walkFileTree(Paths.get(dirName), new MyFileVisitor(name)).toString());
            bwr.write(result);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }

    /**
     * Loading apps properties.
     */
    private void setUp(String logName) {
        ClassLoader loader = Settings.class.getClassLoader();
        InputStream io = loader.getResourceAsStream("app.properties");
        this.settings.load(io);
        currentDir = this.settings.getValue("defaultSearchDir");
        currentLogDir = this.settings.getValue("defaultLogDir");
        File logDir = new File(currentLogDir);
        logDir.mkdir();
        log = new File(currentLogDir, logName);

    }


    class MyFileVisitor extends SimpleFileVisitor<Path> {
        public String name;

        public MyFileVisitor(String name) {
            this.name = name;

        }


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
                                //writeToLog(foundByMask.toString());
                            }
                        }
                        break;

                    case "-f":
                        File foundExact = new File(path.toString());
                        if (foundExact.isFile() & foundExact.getName().equals(name)) {
                            System.out.println(foundExact.toString());
                            // writeToLog(foundExact.toString());
                        }
                        break;

                    case "-r":
                        File foundPart = new File(path.toString());
                        if (foundPart.isFile() & foundPart.getName().contains(name)) {
                            System.out.println(foundPart.toString());
                            //writeToLog(foundPart.toString());
                        }
                        break;
                }
            }


            return FileVisitResult.CONTINUE;


        }


        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attribs) throws IOException {
            if (attribs.isDirectory() & !attribs.isOther()) {
                System.out.println(path);
            }
            return FileVisitResult.CONTINUE;

        }

        public FileVisitResult visitFileFailed(Path path, IOException ioe) throws IOException {
            System.err.printf("Visiting failed for %s\n", path);
            return FileVisitResult.SKIP_SUBTREE;
        }
    }

    //public void writeToLog(String input) throws IOException  {
    //   try (BufferedWriter bwr = new BufferedWriter(new FileWriter(log))) {
    //       bwr.write(input);

    //   }


}

