package com.saatchiNSaatchi.aiDashboard.commands;

import com.saatchiNSaatchi.aiDashboard.Models.AIUsage;
import com.saatchiNSaatchi.aiDashboard.controllers.AdminController;
import java.time.LocalDate;
import java.util.List;
import lombok.Setter;

@Setter
public class GetAIStatsCommand implements Command{

    private Long teamId;

    @Override
    public List<AIUsage> execute() {
        return AdminController.getAIUsageDataByTeam(this.teamId, null, null);
    }
}
