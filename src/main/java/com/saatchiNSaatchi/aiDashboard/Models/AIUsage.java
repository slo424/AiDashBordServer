package com.saatchiNSaatchi.aiDashboard.Models;

import java.time.LocalDate;
import java.util.Date;
import lombok.Getter;

@Getter
public class AIUsage {
    private Long id;

    private Long teamId;

    private String aiModel;

    private Long costPerMonthInCents;

    private LocalDate date;

    public AIUsage(Long id, Long teamId, String aiModel, Long costPerMonthInCents) {
        this.id = id;
        this.teamId = teamId;
        this.aiModel = aiModel;
        this.costPerMonthInCents = costPerMonthInCents;
        this.date = LocalDate.now().minusDays((long)(Math.random() * 40));
    }
}
