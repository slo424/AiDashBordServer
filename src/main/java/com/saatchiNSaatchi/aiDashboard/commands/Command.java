package com.saatchiNSaatchi.aiDashboard.commands;

import java.time.LocalDate;
import java.time.ZoneId;

public interface Command {

    final long MILISEC_IN_SEC = 1000;
    final long SEC_IN_HOURS = 3600;
    final long HOURS_IN_DAYS = 24;

    public Object execute();

    static Long localDateToMilliseconds(LocalDate date) {
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    static Long getDayDiffInMiliseconds(LocalDate start, LocalDate end) {
        if (start != null && end != null)
            return (localDateToMilliseconds(end) - localDateToMilliseconds(start)) / (MILISEC_IN_SEC * SEC_IN_HOURS * HOURS_IN_DAYS);
        return -1L;
    }
}
