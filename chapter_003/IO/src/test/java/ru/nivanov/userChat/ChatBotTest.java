package ru.nivanov.userChat;
import java.io.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
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
		try (RandomAccessFile raf = new RandomAccessFile("botans.txt", "r");) {
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