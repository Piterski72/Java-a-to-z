package ru.nivanov.sheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ru.nivanov.JsoupParser;
import ru.nivanov.base.DBaseManager;
import ru.nivanov.model.Vacancy;

import java.util.Set;

/**
 * Created by Nikolay Ivanov on 27.09.2017.
 */
@SuppressWarnings("WeakerAccess")
public class SearchVacJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        DBaseManager manager = new DBaseManager();

        long lastVisit = manager.getLastVisit();

        JsoupParser example = new JsoupParser();
        Set<Vacancy> vacancySet = example.searchVacancy(lastVisit);
        manager.addVacancy(vacancySet);
        manager.closeConnection();

    }
}
