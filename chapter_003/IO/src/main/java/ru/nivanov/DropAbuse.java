package ru.nivanov;
import java.io.*;

/**
 * Programm removes abuse word from input flow.
 * @author nivanov.
 * @since
 * @version
 */
public class DropAbuse {
    /**
     * method checks and remove abuse word from input flow.
     * @param in is input stream.
     * @param out is output stream.
     * @param abuse is abuse words massive.
     * @throws IOException ..
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {
        boolean check = true;
		int c = 0;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in));
             PrintStream ps = new PrintStream(out)) {
            while (check) {
                for (int i = 0; i < abuse.length; i++) {
					char[] cbuf = new char[abuse[i].length()];
					br.mark(abuse[i].length());
					br.read(cbuf, 0, abuse[i].length());
					if (!abuse[i].equalsIgnoreCase(new String(cbuf))) {
						br.reset();
					}
				}
				c = br.read();
				if (c == -1) {
					check = false;
				} else {
					ps.print((char) c);
				}
			}
		}
	}
}