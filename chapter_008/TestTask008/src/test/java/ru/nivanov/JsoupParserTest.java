package ru.nivanov;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 15.10.2017.
 */
public class JsoupParserTest {

    /**
     * Test for converting string "сегодня" to date.
     * @throws Exception ..
     */
    @Test
    public void whenConvertToDateThenReturnResult() throws Exception {

        final int year = 2017;
        final int month = 9;
        final int day = 13;
        final int hour = 23;
        final int minute = 28;

        final int substEnd = 16;


        JsoupParser underTest = new JsoupParser();
        long result = underTest.textToDateToLong("13 окт 17, 23:28");

        GregorianCalendar cal = new GregorianCalendar(year, month, day, hour, minute, 0);
        Date expectedDate = cal.getTime();

        Date resultDate = new Date(result);

        String res = resultDate.toString().substring(0, substEnd);
        String exp = expectedDate.toString().substring(0, substEnd);

        assertThat(res, is(exp));

    }


}