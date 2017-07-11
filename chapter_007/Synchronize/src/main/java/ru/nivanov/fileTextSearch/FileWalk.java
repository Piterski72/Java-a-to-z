package ru.nivanov.fileTextSearch;

import java.io.File;

/**
 * Created by Nikolay Ivanov on 03.07.2017.
 */
class FileWalk implements Runnable {

    static final File DUMMY = new File("");
    private final SyncTaskQueue<File> queue;
    private final File startDirectory;

    /**
     * Constructor.
     * @param queue ..
     * @param startDirectory ..
     */
    FileWalk(SyncTaskQueue<File> queue, File startDirectory) {
        this.queue = queue;
        this.startDirectory = startDirectory;
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
        loadFiles(this.startDirectory);
        queue.put(DUMMY);
    }

    /**
     * Walking over directiory and putting files in queue.
     * @param directory ..
     */
    private void loadFiles(File directory) {

        File[] files = directory.listFiles();

        if (files != null) {

            for (File file : files) {
                if (file.isDirectory() && file.canRead() && !file.isHidden()) {
                    loadFiles(file);
                } else if (file.isFile()) {
                    if (!StartFindText.isFoundFinished()) {
                        queue.put(file);
                    } else {
                        return;
                    }
                }
            }

        }
    }
}
