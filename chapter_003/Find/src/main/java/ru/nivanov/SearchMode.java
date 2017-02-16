package ru.nivanov;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Created by Nikolay Ivanov on 16.02.2017.
 */
class SearchMode {
    private static ArrayList<String> forLog = new ArrayList<>();
    private final int kol = 3;
    private SearchAction[] actions = new SearchAction[kol];

    /**
     * Filling actions.
     */
    void fillActions() {
        actions[0] = this.new SearchByMask();
        actions[1] = this.new SearchExact();
        actions[2] = this.new SearchPartial();
    }

    /**
     * @param selection ..
     * @param searchName ..
     * @param path ..
     * @return ..
     */
    ArrayList<String> modeSelect(String selection, String searchName, Path path) {
        for (SearchAction action : actions) {
            if (selection.equals(action.getId())) {
                action.execute(searchName, path);


            }
        }


        return forLog;
    }

    /**
     * inner class ..
     */
    private class SearchByMask implements SearchAction {
        private String id = "-m";

        /**
         * Getter for id.
         * @return class id
         */
        public String getId() {
            return this.id;
        }

        /**
         * @param name ..
         * @param path ..
         * @return ..
         */
        public ArrayList<String> execute(String name, Path path) {
            File foundByMask = new File(path.toString());
            if (name.matches("\\*\\..+")) {
                String mask = name.substring(1);
                String expect = foundByMask.toString();
                if (foundByMask.isFile() & expect.substring(expect.length() - mask.length()).equals(mask)) {
                    System.out.println(foundByMask.toString());
                    forLog.add(foundByMask.toString());
                }
            }

            return forLog;
        }
    }

    /**
     * inner class ..
     */
    private class SearchExact implements SearchAction {
        private String id = "-f";

        /**
         * Getter for id.
         * @return class id
         */
        public String getId() {
            return this.id;
        }

        /**
         * @param name ..
         * @param path ..
         * @return ..
         */
        public ArrayList<String> execute(String name, Path path) {
            File foundExact = new File(path.toString());
            if (foundExact.isFile() & foundExact.getName().equals(name)) {
                System.out.println(foundExact.toString());
                forLog.add(foundExact.toString());
            }
            return forLog;
        }
    }

    /**
     * inner class ..
     */
    private class SearchPartial implements SearchAction {
        private String id = "-r";

        /**
         * Getter for id.
         * @return class id
         */
        public String getId() {
            return this.id;
        }

        /**
         * @param name ..
         * @param path ..
         * @return ..
         */
        public ArrayList<String> execute(String name, Path path) {
            File foundPart = new File(path.toString());
            if (foundPart.isFile() & foundPart.getName().contains(name)) {
                System.out.println(foundPart.toString());
                forLog.add(foundPart.toString());
            }
            return forLog;
        }
    }
}
