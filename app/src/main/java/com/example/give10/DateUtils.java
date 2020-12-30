package com.example.give10;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    public static String getFormattedDate (Long selection)
    {
        long offsetFromUTC =
                TimeZone.getDefault ().getOffset (new Date().getTime ()) * -1;

        @SuppressWarnings ("SpellCheckingInspection")
        SimpleDateFormat sdf = new SimpleDateFormat ("MM/dd/yyyy",
                Locale.getDefault ());

        return sdf.format (new Date (selection + offsetFromUTC));
    }

}