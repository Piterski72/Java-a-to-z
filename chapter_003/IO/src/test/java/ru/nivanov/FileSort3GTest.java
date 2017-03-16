package ru.nivanov;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * FileSort3GTest class.
 * @author nivanov.
 */
public class FileSort3GTest {
    /**
     * Test for external sorting.
     * @throws IOException ..
     */
    @Test
    public void whenSortCorrectThenReturnResult() throws IOException {
        final String sep = System.getProperty("line.separator");
        final String[] unSortedLines = {"55555", "4444", "333", "22", "1", "666666", "7777777", "22"};
        final String[] sortedLines = {"1", "22", "22", "333", "4444", "55555", "666666", "7777777"};
        File input1 = new File("file1.txt");
        File output1 = new File("file2.txt");
        try (RandomAccessFile raf = new RandomAccessFile(input1, "rw")) {
            for (String line : unSortedLines) {
                raf.writeBytes(String.format("%s%s", line, sep));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        FileSort3G fileSort = new FileSort3G();
        try {
            fileSort.sort(input1, output1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        String[] result = new String[unSortedLines.length];
        try (RandomAccessFile dest1 = new RandomAccessFile(output1, "rw")) {
            String line;
            int count = 0;
            while ((line = dest1.readLine()) != null) {
                result[count++] = line;
            }
            assertThat(result, is(sortedLines));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}