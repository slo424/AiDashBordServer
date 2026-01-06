package com.saatchiNSaatchi.aiDashboard.commands;

import com.saatchiNSaatchi.aiDashboard.Models.AIUsage;
import com.saatchiNSaatchi.aiDashboard.controllers.AdminController;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetAIStatsByTeamIdAndDateCommand implements Command{

    private Long teamId;

    private String startDateStr;


    private String endDateStr;

    @Override
    public List<AIUsage> execute() {
        return AdminController.getAIUsageDataByTeam(this.teamId, startDateStr, endDateStr);
    }
}
