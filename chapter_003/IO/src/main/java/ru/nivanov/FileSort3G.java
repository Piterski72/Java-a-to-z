package ru.nivanov;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Programm uses external sorting method.
 * @author nivanov.
 * @since
 * @version
 */
class FileSort3G {
    private final String sep = System.getProperty("line.separator");
    private boolean unsorted = true;
	/**
	* external sorting method.
	* @param source is input file.
	* @param distance is output file.
	* @throws IOException ..
	*/
	public void sort(File source, File distance) throws IOException {
		File fileOne = new File("fileOne.txt");
		File fileTwo = new File("fileTwo.txt");
		try (RandomAccessFile rafSource = new RandomAccessFile(source, "r");
             RandomAccessFile rafDest = new RandomAccessFile(distance, "rw");
             RandomAccessFile raf1 = new RandomAccessFile(fileOne, "rw");
             RandomAccessFile raf2 = new RandomAccessFile(fileTwo, "rw")) {
            rafSource.seek(0);
            writeToFile(rafSource, rafDest);
				do {
					splitFiles(rafDest, raf1, raf2);
					if (unsorted) {
						mergeFiles(raf1, raf2, rafDest);
					}
				} while (unsorted);
		}
		fileOne.delete();
		fileTwo.delete();
	}
	/**
	* method for splitting files.
	* @param dest is source file.
	* @param firstFile is first file.
	* @param secondFile is second file.
	* @throws IOException ..
	*/
	private void splitFiles(RandomAccessFile dest, RandomAccessFile firstFile, RandomAccessFile secondFile) throws IOException {
		dest.seek(0);
		firstFile.setLength(0);
		secondFile.setLength(0);
		firstFile.seek(0);
		secondFile.seek(0);
		String str = dest.readLine();
		String strNext = "";
		firstFile.writeBytes(String.format("%s%s", str, sep));
		boolean writeFileOneId = true;
		while (dest.getFilePointer() != dest.length()) {
			strNext = dest.readLine();
			if (str.length() <= strNext.length() && writeFileOneId) {
				firstFile.writeBytes(String.format("%s%s", strNext, sep));
			} else if (str.length() > strNext.length() && writeFileOneId) {
				secondFile.writeBytes(String.format("%s%s", strNext, sep));
				writeFileOneId = false;
			} else if (str.length() <= strNext.length() && !writeFileOneId) {
				secondFile.writeBytes(String.format("%s%s", strNext, sep));
			} else if (str.length() > strNext.length() && !writeFileOneId) {
				firstFile.writeBytes(String.format("%s%s", strNext, sep));
				writeFileOneId = true;
			}
			str = strNext;
		}
		firstFile.seek(0);
		secondFile.seek(0);
		dest.setLength(0);
		if (firstFile.readLine() == null) {
			secondFile.seek(0);
			writeToFile(secondFile, dest);
			unsorted = false;
		} else if (secondFile.readLine() == null) {
			firstFile.seek(0);
			writeToFile(firstFile, dest);
			unsorted = false;
		}
	}
	/**
	* method for merging files.
	* @param firstFile is first file.
	* @param secondFile is second file.
	* @param dest is source file.
	* @throws IOException ..
	*/
	private void mergeFiles(RandomAccessFile firstFile, RandomAccessFile secondFile, RandomAccessFile dest) throws IOException {
		dest.seek(0);
		firstFile.seek(0);
		secondFile.seek(0);
		String str1 = firstFile.readLine();
		String str2 = secondFile.readLine();
			while (firstFile.getFilePointer() != firstFile.length() && secondFile.getFilePointer() != secondFile.length()) {
				if (str1.length() <= str2.length()) {
					dest.writeBytes(String.format("%s%s", str1, sep));
					str1 = firstFile.readLine();
				} else if (str1.length() > str2.length()) {
					dest.writeBytes(String.format("%s%s", str2, sep));
					str2 = secondFile.readLine();
				}
			}
			if (secondFile.getFilePointer() == secondFile.length() && firstFile.getFilePointer() != firstFile.length()) {
				if (str1.length() > str2.length()) {
					dest.writeBytes(String.format("%s%s", str2, sep));
					while (str1 != null) {
						dest.writeBytes(String.format("%s%s", str1, sep));
						str1 = firstFile.readLine();
						if (str1 == null) {
							break;
						}
					}
				} else {
					while (str1 != null && str1.length() <= str2.length()) {
						dest.writeBytes(String.format("%s%s", str1, sep));
						str1 = firstFile.readLine();
							if (str1 == null) {
								break;
							}
					}
					if (str1 != null) {
						dest.writeBytes(String.format("%s%s", str2, sep));
						while (firstFile.getFilePointer() <= firstFile.length()) {
							dest.writeBytes(String.format("%s%s", str1, sep));
							str1 = firstFile.readLine();
							if (str1 == null) {
								break;
							}
						}
					} else {
						dest.writeBytes(String.format("%s%s", str2, sep));
					}
				}
			} else if (firstFile.getFilePointer() == firstFile.length() && secondFile.getFilePointer() != secondFile.length()) {
				if (str1.length() < str2.length()) {
					dest.writeBytes(String.format("%s%s", str1, sep));
					while (secondFile.getFilePointer() <= secondFile.length()) {
						dest.writeBytes(String.format("%s%s", str2, sep));
						str2 = secondFile.readLine();
						if (str2 == null) {
							break;
						}
					}
				} else {
					while (str2 != null && str2.length() <= str1.length()) {
						dest.writeBytes(String.format("%s%s", str2, sep));
						str2 = secondFile.readLine();
					}
					if (str2 != null) {
						dest.writeBytes(String.format("%s%s", str1, sep));
						while (secondFile.getFilePointer() <= secondFile.length()) {
							dest.writeBytes(String.format("%s%s", str2, sep));
							str2 = secondFile.readLine();
							if (str2 == null) {
								break;
							}
						}
					} else {
						dest.writeBytes(String.format("%s%s", str1, sep));
					}
				}
			}
	}
	/**
	* method for writing data to files.
	* @param input is input file.
	* @param output is output file.
	* @throws IOException ..
	*/
	private void writeToFile(RandomAccessFile input, RandomAccessFile output) throws IOException {
		while (input.getFilePointer() < input.length()) {
			output.writeBytes(String.format("%s%s", input.readLine(), sep));
		}
	}
}