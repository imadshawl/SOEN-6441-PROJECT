package components;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * This class is responsible for implementing static date time util methods.
 */
public class DateTimeUtils {

    /**
     * This static method converts time in millis to standard readable date string.
     * The format that it converts to is "YYYY-MM-DD".
     *
     * @param timeMillis - Time in millis to convert
     * @return Returns date string in the format "YYYY-MM-DD"
     */
    public static String getDateStringFromMillis(final Long timeMillis) {
        Instant instance = Instant.ofEpochSecond(timeMillis);
        return LocalDateTime
                .ofInstant(instance, ZoneId.of("America/Montreal"))
                .format(DateTimeFormatter.ISO_DATE);
    }
}
