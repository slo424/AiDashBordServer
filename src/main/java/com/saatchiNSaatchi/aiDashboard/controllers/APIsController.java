package com.saatchiNSaatchi.aiDashboard.controllers;

import com.saatchiNSaatchi.aiDashboard.commands.Command;
import com.saatchiNSaatchi.aiDashboard.commands.GetAIStatsByTeamIdAndDateCommand;
import com.saatchiNSaatchi.aiDashboard.commands.GetAIStatsCommand;
import com.saatchiNSaatchi.aiDashboard.commands.GetAIUsageTeamIdsCommand;
import com.saatchiNSaatchi.aiDashboard.views.AiStatView;
import com.saatchiNSaatchi.aiDashboard.views.TeamIdsView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class APIsController {
    public static final int TOP_ENTRIES_SHOWN = 3;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping("/usage")
    public AiStatView getAIStats( @RequestBody @Validated GetAIStatsCommand getAIStatsCommand) {
        getAIStatsCommand.setTeamId(0L);
        return new AiStatView(0L, getAIStatsCommand.execute(), 7L);
    }

    @GetMapping(value = {"/usage/", "usage/{teamId}"})
    public AiStatView getAIStats(@PathVariable Optional<Long> teamId, GetAIStatsCommand getAIStatsCommand) {
        if (teamId != null && teamId.isPresent()) {
            getAIStatsCommand.setTeamId(teamId.get());
            return new AiStatView(teamId.get(), getAIStatsCommand.execute(), 7L);
        }
        return new AiStatView("Invalid request. Team ID is missing from request URL.");
    }

    @PostMapping(value = {"/usage/", "usage/{teamId}"})
    public AiStatView getAIStatsByTeamAndDate(@PathVariable Optional<Long> teamId, @RequestBody @Validated GetAIStatsByTeamIdAndDateCommand getAIStatsCommand) {
        if (teamId != null && teamId.isPresent()) {
            String startDateStr = getAIStatsCommand.getStartDateStr();
            String endDateStr = getAIStatsCommand.getEndDateStr();
            LocalDate start = startDateStr != null && startDateStr.length() > 0 ? LocalDate.parse(startDateStr, formatter) : null;
            LocalDate end = endDateStr != null && endDateStr.length() > 0 ? LocalDate.parse(endDateStr, formatter) : null;
//            long diffInDays = Command.getDayDiffInMiliseconds(start, end);

            getAIStatsCommand.setTeamId(teamId.get());
            return new AiStatView(teamId.get(), getAIStatsCommand.execute(), Command.getDayDiffInMiliseconds(start, end));
        }
        return new AiStatView("Invalid request. Team ID is missing from request URL.");
    }

    @GetMapping("usageTeams")
    public TeamIdsView getAIUsageTeamIds(GetAIUsageTeamIdsCommand getAIUsageTeamIdsCommand) {
        return new TeamIdsView(getAIUsageTeamIdsCommand.execute());
    }
}
