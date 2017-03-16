package ru.nivanov.userChat;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * Programm sinulates console chat.
 * @author nivanov.
 */
class ChatBot {
    private final File answers = new File("botAns.txt");

    /**
     * counting number of string lines in file.
     * @return count ..
     * @throws IOException ..
     */
    private int lineNumber() throws IOException {
        int count = 0;
        try (RandomAccessFile raf = new RandomAccessFile(this.answers, "r")) {
            while (raf.getFilePointer() != raf.length()) {
                raf.readLine();
                count++;
            }
        }
        return count;
    }

    /**
     * starting chat bot.
     * @return answ getting bot random answer ..
     * @throws IOException ..
     */
    public String startBot() throws IOException {
        @SuppressWarnings("UnnecessaryLocalVariable") String answ = generateAnswer();
        return answ;
    }

    /**
     * generating bot random answer.
     * @return resulst getting bot random answer ..
     * @throws IOException ..
     */
    private String generateAnswer() throws IOException {
        int j = lineNumber();
        int i = 0;
        String result = "";
        Random rnd = new Random();
        int rndNumber = rnd.nextInt(j);
        try (RandomAccessFile raf = new RandomAccessFile(this.answers, "r")) {
            while (raf.getFilePointer() != raf.length()) {
                result = raf.readLine();
                i++;
                if ((i - 1) == rndNumber) {
                    break;
                }
            }
        }
        return result;
    }
}