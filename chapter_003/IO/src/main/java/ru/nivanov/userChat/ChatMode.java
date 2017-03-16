package ru.nivanov.userChat;

import java.io.IOException;

/**
 * Programm switches console chat mode (normal, suspen, wakeup).
 * @author nivanov.
 */
public class ChatMode {
    private final ChatBot chat;
    private final int kol = 3;
    private final BotAction[] actions = new BotAction[kol];
    private boolean suspended = false;

    public ChatMode(ChatBot chat) {
        this.chat = chat;
    }

    /**
     * Filling actions.
     */
    public void fillActions() {
        actions[0] = this.new NormalMode();
        actions[1] = this.new SuspendMode();
        actions[2] = this.new WakeUpMode();
    }

    /**
     * method for chat mode selection.
     * @param phrase is input.
     * @return result.
     * @throws IOException ..
     */
    public String modeSelect(String phrase) throws IOException {
        String result = "error";
        boolean match = false;
        for (int i = 0; i < actions.length; i++) {
            if (phrase.equalsIgnoreCase(actions[i].getId())) {
                result = actions[i].execute();
                match = true;
            }
        }
        if (!match) {
            result = actions[0].execute();
        }
        return result;
    }

    /**
     * NormalMode inner class.
     * @author nivanov.
     */
    private class NormalMode implements BotAction {
        private final String id = "normal";

        /**
         * Getter for id.
         * @return class id
         */
        public String getId() {
            return this.id;
        }

        /**
         * Executing normal operation.
         * @return string result
         * @throws IOException ..
         */
        public String execute() throws IOException {
            String res = "";
            if (!suspended) {
                res = chat.startBot();
            } else {
                res = "";
            }
            return res;
        }
    }

    /**
     * SuspendlMode inner class.
     * @author nivanov.
     */
    private class SuspendMode implements BotAction {
        private final String id = "stop";

        /**
         * Getter for id.
         * @return class id
         */
        public String getId() {
            return this.id;
        }

        /**
         * Executing normal operation.
         * @return string result
         * @throws IOException ..
         */
        public String execute() throws IOException {
            suspended = true;
            return "";
        }
    }

    /**
     * WakeUpMode inner class.
     * @author nivanov.
     */
    private class WakeUpMode implements BotAction {
        private final String id = "go";

        /**
         * Getter for id.
         * @return class id
         */
        public String getId() {
            return this.id;
        }

        /**
         * Executing normal operation.
         * @return string result
         * @throws IOException ..
         */
        public String execute() throws IOException {
            suspended = false;
            String res = chat.startBot();
            return res;
        }
    }
}