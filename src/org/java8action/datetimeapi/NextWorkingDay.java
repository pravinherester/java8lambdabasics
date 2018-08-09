package org.java8action.datetimeapi;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextWorkingDay implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        // TODO Auto-generated method stub
        DayOfWeek dow=DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int daytoAdd=1;
        if(dow==DayOfWeek.FRIDAY){
            daytoAdd=3;
        }
        else if(dow==DayOfWeek.SATURDAY){
            daytoAdd=2;
        }
        
        return temporal.plus(daytoAdd,ChronoUnit.DAYS);
    }

}
