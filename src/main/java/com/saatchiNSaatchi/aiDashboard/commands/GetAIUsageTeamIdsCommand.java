package com.saatchiNSaatchi.aiDashboard.commands;

import com.saatchiNSaatchi.aiDashboard.controllers.AdminController;
import java.util.List;

public class GetAIUsageTeamIdsCommand implements Command{
    @Override
    public String execute() {
        List<Long> list = AdminController.getUsageTeamIdsRange();
        return list.get(0) + "-" + list.get(list.size()-1);
    }
}
