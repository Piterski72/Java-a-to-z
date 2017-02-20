package ru.nivanov.userChat;

import java.io.IOException;
/**
 * BotAction interface.
 * @author nivanov.
 * @since
 * @version
 */
interface BotAction {
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