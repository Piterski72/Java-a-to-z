package ru.nivanov.sheduler;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Nikolay Ivanov on 27.09.2017.
 */
public class QuartzSched {

    /**
     * Starting schedule.
     */
    public void startSchedule() {
        //BasicConfigurator.configure();

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();

            // define the job and tie it to our SearchVacJob class
            JobDetail job = newJob(SearchVacJob.class).withIdentity("job1", "group1").build();

            // Trigger the job to run now, and then repeat every day at 12-00
            CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(
                    cronSchedule("0/15 * * * * ?")).forJob("job1", "group1").build();

            // Tell quartz to schedule the job using our trigger

            scheduler.scheduleJob(job, trigger);

            try {
                final long delay = 60000;
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            scheduler.shutdown(true);

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
