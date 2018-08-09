package org.java8action.datetimeapi;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextWorkingHour implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        
        int hour=temporal.get(ChronoField.HOUR_OF_DAY);
        int addhour=1;
        if(hour>=18)
        {
            addhour=15;
        }
        // TODO Auto-generated method stub
        return temporal.plus(addhour,ChronoUnit.HOURS);
    }

}
