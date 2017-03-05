package kamilhalko.com.driveanalyzer.utils;

import org.joda.time.DateTime;

public class DateUtils {

    public static long now(){
        return DateTime.now().getMillis();
    }
}
