package ru.nivanov;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nikolay Ivanov on 13.04.2017.
 */
class UserConvert {
    /**
     * Converting list to map.
     * @param list ..
     * @return map.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        Iterator<User> iterator = list.iterator();
        User currentUser;
        while (iterator.hasNext()) {
            currentUser = iterator.next();
            result.put(currentUser.getId(), currentUser);
        }
        return result;
    }
}
