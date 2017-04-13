package kamilhalko.com.driveanalyzer.utils;

import org.joda.time.DateTime;

public class DateUtils {

    public static long now(){
        return DateTime.now().getMillis();
    }

    public static String formatTime(double time) {
        int seconds = (int) time;

        final int MINUTES_IN_AN_HOUR = 60;
        final int SECONDS_IN_A_MINUTE = 60;

        int minutes = seconds / SECONDS_IN_A_MINUTE;
        seconds -= minutes * SECONDS_IN_A_MINUTE;

        int hours = minutes / MINUTES_IN_AN_HOUR;
        minutes -= hours * MINUTES_IN_AN_HOUR;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
