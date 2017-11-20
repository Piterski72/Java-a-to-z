package ru.nivanov.model;

import java.util.Date;
import java.util.Map;

/**
 * Created by Nikolay Ivanov on 20.11.2017.
 */
public class TableHtml {

    /**
     * Get formatted table for html output.
     * @return ..
     */
    public String getTable() {

        StringBuilder sb = new StringBuilder();
        Map<Integer, User> map = UserStore.getBase().showUsers();
        for (Map.Entry entry : map.entrySet()) {
            int id = (int) entry.getKey();
            User user = (User) entry.getValue();

            sb.append(String.format("id= %d name= %s login= %s email= %s date= %s", id, user.getName(), user.getLogin(),
                    user.getEmail(), new Date(user.getCreateDate())));
            sb.append("<br>");

        }
        return sb.toString();

    }

}
