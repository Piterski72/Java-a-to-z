package ru.nivanov.userChat;
import java.io.*;

/**
 * Programm sinulates console chat.
 * @author nivanov.
 * @since
 * @version
 */
 public class ConsoleChat {
	private Reader input;
	private ChatBot chat;
	private String sep = System.getProperty("line.separator");
	public ConsoleChat(Reader input, ChatBot chat) {
		this.input = input;
		this.chat = chat;
	}
	/**
	 * realization.
	 *
	 * @throws IOException ..
	 */
	public void init() throws IOException {
		ChatMode operation = new ChatMode(this.chat);
		operation.fillActions();
		File fileOne = new File("chatlog.txt");
		try (BufferedWriter brw = new BufferedWriter(new FileWriter(fileOne));
			BufferedReader br = new BufferedReader(this.input);) {
			String inputData = "";
			String botAnswer = "";
			while (!"exit".equalsIgnoreCase(inputData)) {
				inputData = br.readLine();
				botAnswer = operation.modeSelect(inputData);
				brw.write(String.format("user: %s%s", inputData, sep));
				brw.write(String.format("bot: %s%s", botAnswer, sep));
			}
		}
	}
	/**
	* main method.
	* @param args input param
	*/
	public static void main(String[] args) {
		try (Reader input = new InputStreamReader(System.in)) {
			ChatBot chat = new ChatBot();
			new ConsoleChat(input, chat).init();
		} catch (IOException ioe) {
			 ioe.printStackTrace();
		}
	}
}