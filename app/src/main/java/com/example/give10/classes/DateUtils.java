package com.example.give10.classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    public static String getFormattedDate (Long selection)
    {
        long offsetFromUTC =
                TimeZone.getDefault ().getOffset (new Date().getTime ()) * -1;

        SimpleDateFormat sdf = new SimpleDateFormat ("MM/dd/yyyy",
                Locale.getDefault ());

        return sdf.format (new Date (selection + offsetFromUTC));
    }

}
