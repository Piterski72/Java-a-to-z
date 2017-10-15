package ru.nivanov;

import ru.nivanov.base.DBaseManager;
import ru.nivanov.sheduler.QuartzSched;

/**
 * Created by Nikolay Ivanov on 06.10.2017.
 */
public class StartVacancySearch {

    /**
     * Main method.
     * @param args ..
     */
    public static void main(String[] args) {

        DBaseManager dBaseManager = new DBaseManager();

        dBaseManager.init();

        QuartzSched quartzSched = new QuartzSched();

        quartzSched.startSchedule();

        dBaseManager.showVacancies();

        dBaseManager.closeConnection();

    }
}
