package ru.nivanov.fileTextSearch;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Nikolay Ivanov on 03.07.2017.
 */
class TextSearch implements Runnable {
    //private final BlockingQueue<File> queue;
    private final SyncTaskQueue<File> queue;
    private final String text;
    private final String searchKey;

    /**
     * Constructor.
     * @param queue ..
     * @param text ..
     */
    TextSearch(SyncTaskQueue<File> queue, String text, String searchCondition) {
        this.queue = queue;
        this.text = text;
        this.searchKey = searchCondition;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     * @see Thread#run()
     */
    @Override
    public void run() {


        try {
            boolean done = false;
            while (!done) {
                File file = queue.take();
                if (file == FileWalk.DUMMY) {
                    queue.put(file);
                    done = true;
                } else {
                    search(file, this.searchKey);

                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Searching text in file.
     * @param file ..
     * @param searchKey ..
     * @throws IOException ..
     */
    private void search(File file, String searchKey) throws IOException {
        try (Scanner in = new Scanner(file)) {
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(text)) {
                    System.out.println(String.format("%s: line# %d: text: %s", file.getPath(), lineNumber, line));
                    if (!searchKey.equals("a")) {
                        StartFindText.setFoundFinished(true);
                    }
                }
            }
        }
    }
}
