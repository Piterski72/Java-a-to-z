package ru.nivanov.fileTextSearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Nikolay Ivanov on 03.07.2017.
 */
public class StartFindText {

    private static final List<Thread> THREAD_LIST = new ArrayList<>();
    private static volatile boolean foundFinished;

    /**
     * Search key getter.
     * @return ..
     */
    public static boolean isFoundFinished() {
        return foundFinished;
    }

    /**
     * Search Key setter (stop searching when first result found).
     * @param foundFinished ..
     */
    static void setFoundFinished(boolean foundFinished) {
        StartFindText.foundFinished = foundFinished;
    }

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter starting directory (e.g.  c:/java)");
        String directory = in.nextLine();
        if (!new File(directory).isDirectory()) {
            System.out.println("error, no such directory!");
            return;
        }

        System.out.println("Enter text to find");
        String text = in.nextLine();

        System.out.println("Search all or find first? (a to search all) ");
        String searchFlag = in.nextLine();


        final int FILE_QUEUE_SIZE = 10;
        final int SEARCH_THREADS = 40;

        SyncTaskQueue<File> queue = new SyncTaskQueue<>(FILE_QUEUE_SIZE);

        FileWalk fileWalker = new FileWalk(queue, new File(directory));
        Thread dirWalk = new Thread(fileWalker);

        THREAD_LIST.add(dirWalk);

        dirWalk.start();


        for (int i = 1; i <= SEARCH_THREADS; i++) {
            Thread current = new Thread(new TextSearch(queue, text, searchFlag),
                    String.format("thread# %s", String.valueOf(i)));
            THREAD_LIST.add(current);
            THREAD_LIST.get(i).start();
        }

        for (Thread value : THREAD_LIST) {
            try {
                value.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
