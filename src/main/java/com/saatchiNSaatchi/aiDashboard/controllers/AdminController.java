package com.saatchiNSaatchi.aiDashboard.controllers;

import com.saatchiNSaatchi.aiDashboard.Models.AIUsage;
//import jakarta.validation.constraints.Null;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Null;

public interface AdminController {

    List<AIUsage> aiUsageList = new ArrayList<>();

    static void initAIData() {
        aiUsageList.add(new AIUsage(1L, 1L, "ChatGPT", 2000L));
        aiUsageList.add(new AIUsage(2L, 2L, "Fliki", 1700L));
        aiUsageList.add(new AIUsage(3L, 1L, "Browse", 1900L));
        aiUsageList.add(new AIUsage(4L, 1L, "Notion", 2000L));
        aiUsageList.add(new AIUsage(5L, 2L, "ChatGPT", 2000L));
        aiUsageList.add(new AIUsage(6L, 1L, "ChatGPT", 2000L));
        aiUsageList.add(new AIUsage(7L, 3L, "ChatGPT", 2000L));
        aiUsageList.add(new AIUsage(8L, 4L, "Fliki", 1700L));
        aiUsageList.add(new AIUsage(9L, 3L, "Browse", 1900L));
        aiUsageList.add(new AIUsage(10L, 4L, "Notion", 2000L));
        aiUsageList.add(new AIUsage(11L, 2L, "ChatGPT", 2000L));
        aiUsageList.add(new AIUsage(12L, 1L, "ChatGPT", 2000L));
    }

    static List<AIUsage> getAIUsageDataByTeam(Long teamId, @Null String startDateStr, @Null String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        formatter = formatter.withLocale()

        if (aiUsageList.size() > 0) {
            LocalDate start = startDateStr != null && startDateStr.length() > 0 ? LocalDate.parse(startDateStr, formatter) : null;
            LocalDate end = endDateStr != null && endDateStr.length() > 0 ? LocalDate.parse(endDateStr, formatter) : null;

            if (teamId == 0) {
                if (start == null && end == null) {
                    return aiUsageList.stream()
                            .filter(aiUsage -> aiUsage.getDate().isAfter(LocalDate.now().minusDays(7)))
                            .toList();
                }
                return aiUsageList.stream()
                        .filter(aiUsage -> aiUsage.getDate().isAfter(start) && aiUsage.getDate().isBefore(end))
                        .toList();
            }
            if (aiUsageList.size() > 0) {
                if (start == null && end == null) {
                    return aiUsageList.stream()
                            .filter(aiUsage -> aiUsage.getTeamId() == teamId &&
                                                aiUsage.getDate().isAfter(LocalDate.now().minusDays(7)))
                            .toList();
                }
                return aiUsageList.stream()
                        .filter(aiUsage -> aiUsage.getTeamId() == teamId &&
                                aiUsage.getDate().isAfter(start) &&
                                aiUsage.getDate().isBefore(end))
                        .toList();
            }
        }
        return new ArrayList<>();
    }

    static List<Long> getUsageTeamIdsRange() {
        return aiUsageList.stream()
                .map(aiUsage -> aiUsage.getTeamId())
                .distinct()
                .sorted()
                .toList();
    }
}
