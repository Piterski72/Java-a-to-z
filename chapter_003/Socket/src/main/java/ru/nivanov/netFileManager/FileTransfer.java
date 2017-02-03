package ru.nivanov.netFileManager;

import java.io.*;

/**
 * File trnsfer operations.
 * @author nivanov.
 */
class FileTransfer {
    private final int block = 4096;

    /**
     * Writing file to output stream.
     * @param file ..
     * @param don ..
     * @throws IOException ..
     */
    void fileToStream(File file, DataOutputStream don) throws IOException {
        try (FileInputStream finst = new FileInputStream(file)) {
            int data;
            byte[] buf = new byte[block];
            while ((data = finst.read(buf, 0, block)) != -1) {
                don.write(buf, 0, data);
                don.flush();
            }
        }
    }

    /**
     * Writing input stream to file.
     * @param din ..
     * @param file ..
     * @param fileLength ..
     * @throws IOException ..
     */
    void streamToFile(DataInputStream din, File file, int fileLength) throws IOException {
        try (FileOutputStream foutst = new FileOutputStream(file)) {
            int data;
            byte[] buff = new byte[block];
            while (fileLength > 0) {
                data = din.read(buff);
                foutst.write(buff, 0, data);
                foutst.flush();
                fileLength -= data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}