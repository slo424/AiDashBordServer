package com.saatchiNSaatchi.aiDashboard.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saatchiNSaatchi.aiDashboard.Models.AIUsage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import static com.saatchiNSaatchi.aiDashboard.controllers.APIsController.TOP_ENTRIES_SHOWN;

@Getter
public class AiStatView {
    private final Long teamId;
    private final int totalCalls;
    private final int tokensConsumed;
    private final long estimatedCostInCents;
    private final List<String> topModels;
    private final String period;
    private final String message;

    public AiStatView(@JsonProperty("estimatedCostInCents") long estimatedCostInCents, @JsonProperty("message") String message,
                      @JsonProperty("period") String period, @JsonProperty("teamId") long teamId,
                      @JsonProperty("tokensConsumed") int tokensConsumed, @JsonProperty("totalCalls") int totalCalls) {
        this.teamId = teamId;
        this.totalCalls = totalCalls;
        this.tokensConsumed = tokensConsumed;
        this.estimatedCostInCents = estimatedCostInCents;
        this.topModels = new ArrayList<>();
        this.period = period;
        this.message = message;
    }

    public AiStatView(Long teamId, List<AIUsage> list, Long daysDiff) {
        this.teamId = teamId;
        this.totalCalls = list.size();
        this.tokensConsumed = list.size() / 2;
        this.estimatedCostInCents = getEstCost(list);
        this.topModels = getTopModels(list);
        this.period = "Last " + daysDiff + " days";
        this.message = list.size() > 0 ? "" : "No AI Usage record is found for " +
                (teamId > 0 ? "Team ID: " + teamId : "All teams") + " for the selected period";
    }

    public AiStatView(String message) {
        this.teamId = 0L;
        this.totalCalls = 0;
        this.tokensConsumed = 0;
        this.estimatedCostInCents = 0;
        this.topModels = new ArrayList();
        this.period = "";
        this.message = message;
    }

    private long getEstCost(List<AIUsage> list) {
        return list.stream()
                .mapToLong(AIUsage::getCostPerMonthInCents)
                .sum();
    }

    private List<String> getTopModels(List<AIUsage> list) {
        Map<String, Integer> map = new HashMap<>();
        for (AIUsage ai : list) {
            map.put(ai.getAiModel(), map.getOrDefault(ai.getAiModel(), 0)+1);
        }

        return map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // Sort entries by value in reverse order
                .limit(TOP_ENTRIES_SHOWN)                                              // Keep only the top N entries
                .map(stringIntegerEntry -> stringIntegerEntry.getKey() + ": " + stringIntegerEntry.getValue())         // Map to the keys
                .toList();
    }
}
