package com.project.simorgh.twitter.Utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TwitterUtils {

    public static Date parseTwitterUTC(String date) throws ParseException {

        String twitterFormat="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        return sf.parse(date);
    }

    public static String parseTwitterDiffDate(String dateStr) {

        try {
            Date date = parseTwitterUTC(dateStr);
            return tw_toString(date);

        }catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int differenceSeconds(Date date1,Date date2) {

        long datetime1 = date1.getTime();
        long datetime2 = date2.getTime();
        long diffSeconds = (datetime1 - datetime2);
        return (int)diffSeconds;
    }

    public static String tw_toString(Date date) {

        Date now = new Date();
        long timeInterval = -differenceSeconds(date, now) / 1000;

        final int weekTime = 604800;
        final int dayTime  = 86400;
        final int hourTime = 3600;
        final int minTime  = 60;

        long time;

        if (timeInterval >= weekTime || timeInterval < 0) {
            String format = "yyyy/MM/dd";
            SimpleDateFormat sf = new SimpleDateFormat(format);
            return sf.format(date);
        }
        else if (timeInterval > dayTime) {
            time = timeInterval / dayTime;
            return time + "d";
        }
        else if (timeInterval >= hourTime) {
            time = timeInterval / hourTime;
            return time + "h";
        }
        else if (timeInterval >= minTime) {
            time = timeInterval / minTime;
            return time + "m";
        }
        else {
            return timeInterval + "s";
        }

    }
}
