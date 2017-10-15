package ru.nivanov;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.nivanov.model.Vacancy;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

/**
 * Created by Nikolay Ivanov on 03.10.2017.
 */
public class JsoupParser {

    private static final String BASE_URL = "http://www.sql.ru/forum/job-offers";
    private final Set<Vacancy> vacancySet = new HashSet<>();

    /**
     * Method searches vacancies and puts them in set.
     * @param lastStartDate ..
     * @return ..
     */
    public Set<Vacancy> searchVacancy(long lastStartDate) {

        final int five = 5;

        String startUrl = BASE_URL;
        String createDate = "";
        int page = 2;
        long start;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.DAY_OF_YEAR, 1);
        if (lastStartDate == 0) {
            start = cal.getTime().getTime();
        } else {
            start = lastStartDate;
        }

        do {
            Document doc;
            try {
                doc = Jsoup.connect(startUrl).get();

                Elements elements = doc.select(
                        "table.forumTable tr:has(td:matches((?i)^.*java(?!script|\\sscript).*$))");
                for (Element el : elements) {
                    Elements children = el.children();

                    String vacUrl = children.get(1).child(0).attr("href");
                    String name = children.get(1).child(0).ownText();
                    String author = children.get(2).child(0).ownText();
                    String authorUrl = children.get(2).child(0).attr("href");
                    createDate = children.get(five).text();
                    if (textToDateToLong(createDate) < start) {
                        break;
                    }
                    this.vacancySet.add(new Vacancy(name, vacUrl, author, authorUrl, textToDateToLong(createDate)));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            startUrl = String.format("%s%s%d", BASE_URL, "/", page);
            page++;

        } while (textToDateToLong(createDate) > start);

        return this.vacancySet;

    }

    /**
     * Text to date converter.
     * @param input ..
     * @return ..
     */
    long textToDateToLong(String input) {

        final int three = 3;
        final int five = 5;

        long result;

        if (input.contains("сегодня")) {

            String time = input.replace("сегодня, ", "");

            Date today = new Date();

            Calendar calToday = new GregorianCalendar();
            calToday.setTime(today);
            calToday.set(HOUR_OF_DAY, Integer.parseInt(time.substring(0, 2)));
            calToday.set(MINUTE, Integer.parseInt(time.substring(three, five)));

            result = calToday.getTimeInMillis();

        } else if (input.contains("вчера")) {

            final int hours = 24;
            final int minutes = 60;
            final int seconds = 60;
            final long multiply = 1000L;


            String time = input.replace("вчера, ", "");
            Date yesterday = new Date(new Date().getTime() - hours * minutes * seconds * multiply);

            Calendar calYesterday = new GregorianCalendar();
            calYesterday.setTime(yesterday);
            calYesterday.set(HOUR_OF_DAY, Integer.parseInt(time.substring(0, 2)));
            calYesterday.set(MINUTE, Integer.parseInt(time.substring(three, five)));

            result = calYesterday.getTimeInMillis();

        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy, HH:mm");

            Date date = null;
            try {
                date = simpleDateFormat.parse(input);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            assert date != null;
            result = date.getTime();
        }

        return result;
    }

}
