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
	private static final String EXIT = "exit";
	public ConsoleChat(Reader input, ChatBot chat) {
		this.input = input;
		this.chat = chat;
	}
	/**
	 * realization.
	 * @param logtxt is log file.
	 * @throws IOException ..
	 */
	public void init(String logtxt) throws IOException {
		ChatMode operation = new ChatMode(this.chat);
		operation.fillActions();
		File log = new File(logtxt);
		try (BufferedWriter brw = new BufferedWriter(new FileWriter(log));
			BufferedReader br = new BufferedReader(this.input);) {
			String inputData = "";
			String botAnswer = "";
			while (!EXIT.equalsIgnoreCase(inputData)) {
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
			new ConsoleChat(input, chat).init("chatlog.txt");
		} catch (IOException ioe) {
			 ioe.printStackTrace();
		}
	}
}