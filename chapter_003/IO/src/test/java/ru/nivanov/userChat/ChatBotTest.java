package ru.nivanov.userChat;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ChatBotTest class.
 * @author nivanov.
 * @since
 * @version
 */
public class ChatBotTest {
	/**
	* Test for chat bot.
	* @throws IOException ..
	*/
	@Test
	public void whenChatBotCorrectThenReturnResult() throws IOException {
		ChatBot chat = new ChatBot();
		boolean found = false;
		String result = chat.startBot();
		String expect = "";
		try (RandomAccessFile raf = new RandomAccessFile("botans.txt", "r")) {
			while (raf.getFilePointer() != raf.length()) {
				expect = raf.readLine();
				if (expect.equalsIgnoreCase(result)) {
					found = true;
					break;
				}
			}
		}
		assertThat(found, is(true));
	}
}