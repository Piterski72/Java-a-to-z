package ru.nivanov.userChat;
import java.io.*;
/**
 * BotAction interface.
 * @author nivanov.
 * @since
 * @version
 */
 public interface BotAction {
	 /**
	 * Getting string ident for event.
	 * @return id..
	 *
	 */
	 String getId();
	 /**
	 * chat bot acton.
	 * @return string result..
	 * @throws IOException ..
	 */
	 String execute() throws IOException;
}