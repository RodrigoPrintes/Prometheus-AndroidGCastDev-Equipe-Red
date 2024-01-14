package com.prometheus.egp_tpv.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {
    public static String formatTimeString(String inputString) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ssXXX");
            Date date = inputFormat.parse(inputString);

            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
            outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
