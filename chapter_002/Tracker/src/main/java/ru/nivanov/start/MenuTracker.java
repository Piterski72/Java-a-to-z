package ru.nivanov.start;

import ru.nivanov.models.Item;
import ru.nivanov.models.Task;

import java.util.ArrayList;

/**
 * EditItem outer class.
 * @author nivanov.
 */
class EditItem extends BaseAction {
    EditItem(String name) {
        super(name);
    }

    /**
     * key method.
     * @return key..
     */
    public int key() {
        return 2;
    }

    /**
     * Executing edit action.
     * @param input ..
     * @param tracker ..
     */
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Enter task`s id: ");
        String name = input.ask("Enter task`s name: ");
        String desc = input.ask("Enter task`s desc: ");
        tracker.ieditTwo(id, name, desc);
    }
}

/**
 * MenuTracker class.
 * @author nivanov.
 */
public class MenuTracker {
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private final Input input;
    private final Tracker tracker;
    private final ArrayList<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * range create.
     * @return range
     */
    public ArrayList<Integer> getRange() {
        final int leng = 6;
        ArrayList<Integer> ranges = new ArrayList<>(leng);
        for (int i = 0; i < leng; i++) {
            ranges.add(i, i);
        }
        return ranges;
    }

    /**
     * Build menu realization.
     */
    public void fillActions() {
        actions.add(this.new AddItem("Add new task"));
        actions.add(new MenuTracker.ShowItems("Show all tasks"));
        actions.add(new EditItem("Edit item"));
        actions.add(this.new DelItem("Delete task"));
        actions.add(this.new FilterItems("Set filer by name/desc"));
        actions.add(this.new AddItemComments("Add task`s comments"));
    }

    /**
     * Select menu realization.
     * @param key ..
     */
    public void select(int key) {
        try {
            this.actions.get(key).execute(this.input, this.tracker);
        } catch (NullPointerException npe) {
            System.out.println("Incorrect data, please try again");
        }
    }

    /**
     * Show menu realization.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * ShowItems inner static class.
     * @author nivanov.
     */
    private static class ShowItems extends BaseAction {
        ShowItems(String name) {
            super(name);
        }

        /**
         * key method.
         * @return key..
         */
        public int key() {
            return 1;
        }

        /**
         * Executing list action.
         * @param input ..
         * @param tracker ..
         */
        public void execute(Input input, Tracker tracker) {
            ArrayList<Item> res = tracker.getAll();
            for (int j = 0; j < res.size(); j++) {
                System.out.printf("%s %s %s \n", res.get(j).getId(), res.get(j).getName(), res.get(j).getDescription());
                for (int i = 0; i < res.get(j).getComments().size(); i++) {
                    System.out.println(res.get(j).getComments().get(i));
                }
            }
        }
    }

    /**
     * AddItem inner class.
     * @author nivanov.
     */
    private class AddItem extends BaseAction {
        AddItem(String name) {
            super(name);
        }

        /**
         * key method.
         * @return key..
         */
        public int key() {
            return 0;
        }

        /**
         * Executing add action.
         * @param input ..
         * @param tracker ..
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter task`s name: ");
            String desc = input.ask("Enter task`s desc: ");
            tracker.add(new Task(name, desc));
        }
    }

    /**
     * DelItem inner class.
     * @author nivanov.
     */
    private class DelItem extends BaseAction {
        DelItem(String name) {
            super(name);
        }

        /**
         * key method.
         * @return key..
         */
        public int key() {
            return THREE;
        }

        /**
         * Executing delete action.
         * @param input ..
         * @param tracker ..
         */
        public void execute(Input input, Tracker tracker) {
            String itemId = input.ask("Enter Id: ");
            int before = tracker.getAll().size();
            tracker.remove(itemId);
            if (before == tracker.getAll().size()) {
                System.out.println("Wrong id! Deletion imcomplete!");
            }
        }
    }

    /**
     * FilterItems inner class.
     * @author nivanov.
     */
    private class FilterItems extends BaseAction {
        FilterItems(String name) {
            super(name);
        }

        /**
         * key method.
         * @return key..
         */
        public int key() {
            return FOUR;
        }

        /**
         * Executing filtering action.
         * @param input ..
         * @param tracker ..
         */
        public void execute(Input input, Tracker tracker) {
            String cho = input.ask("Choose filtering: name - by name, desc - by description: ");
            switch (cho) {
                case "name":
                    String name = input.ask("Enter name: ");
                    for (Item item : tracker.getByName(name)) {
                        System.out.printf("%s %s %s \n", item.getId(), item.getName(), item.getDescription());
                    }
                    break;
                case "desc":
                    String desc = input.ask("Enter description: ");
                    for (Item item : tracker.getByDesc(desc)) {
                        System.out.printf("%s %s %s \n", item.getId(), item.getName(), item.getDescription());
                    }
                    break;
                default:
                    System.out.println("incorrect choice");
                    break;
            }
        }
    }

    /**
     * AddItemComments inner class.
     * @author nivanov.
     */
    private class AddItemComments extends BaseAction {
        AddItemComments(String name) {
            super(name);
        }

        /**
         * key method.
         * @return key..
         */
        public int key() {
            return FIVE;
        }

        /**
         * Executing add comments action.
         * @param input ..
         * @param tracker ..
         */
        public void execute(Input input, Tracker tracker) {
            String itemId = input.ask("Enter Id: ");
            String comms = input.ask("Enter comment: ");
            tracker.addComment(itemId, comms);
        }
    }
}