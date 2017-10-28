package com.example.maykon.exemplos.Utilidades;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String dateToString(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        Date data = calendar.getTime();

        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String dt = format.format(data);

        return dt;
    }

}