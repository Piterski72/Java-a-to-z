package ru.nivanov.userChat;
import java.io.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
/**
 * ConsoleChatTest class.
 * @author nivanov.
 * @since
 * @version
 */
  public class ConsoleChatTest {
   /**
	* Test for console chat.
	* @throws IOException ..
	*/
	@Test
	public void whenChatCorrectThenReturnResult() throws IOException {
		File textLines = new File("userText.txt");
		File log = new File("chatlog.txt");
		ChatBot chat = mock(ChatBot.class);
		//add the behavior for chatbot
		when(chat.startBot()).thenReturn("575435");
		try (Reader input = new FileReader(textLines)) {
			new ConsoleChat(input, chat).init();
		} catch (IOException ioe) {
			 ioe.printStackTrace();
		}
		//check a minimum 1 call count
		verify(chat, atLeastOnce()).startBot();
		//check for writing to log file
		boolean match = false;
		try (BufferedReader br = new BufferedReader(new FileReader(log))) {
			String result = "";
			while (result != null) {
				result = br.readLine();
				System.out.println(result);
				if (result.equalsIgnoreCase("bot: 575435")) {
					match = true;
					break;
				}
			}
		}
		assertThat(match, is(true));
	}
}