package kamilhalko.com.driveanalyzer.utils;

public class UnitUtils {

    private UnitUtils() {}

    // multiplication factor to convert minutes to seconds
    public static final double MIN_TO_S = 60.0;

    // multiplication factor to convert seconds to minutes
    public static final double S_TO_MIN = 1 / MIN_TO_S;

    // multiplication factor to convert hours to minutes
    public static final double HR_TO_MIN = 60.0;

    // multiplication factor to convert minutes to hours
    public static final double MIN_TO_HR = 1 / HR_TO_MIN;

    // multiplication factor to covert kilometers to meters
    public static final double KM_TO_M = 1000.0;

    // multiplication factor to convert meters to kilometers
    public static final double M_TO_KM = 1 / KM_TO_M;

    // multiplication factor to convert meters per second to kilometers per hour
    public static final double MS_TO_KMH = M_TO_KM / (S_TO_MIN * MIN_TO_HR);
}
