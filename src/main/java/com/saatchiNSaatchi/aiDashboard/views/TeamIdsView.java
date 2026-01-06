package com.saatchiNSaatchi.aiDashboard.views;

import lombok.Getter;

@Getter
public class TeamIdsView {
    private String idRange;
    public TeamIdsView(String idRange) {
        this.idRange = idRange;
    }
}
