package com.soen390.team11.dto;

import java.time.LocalDate;

/**
 * customize report DTO, when amdmin would like to set get the report
 */
public class CustomizeReportDto {
    private LocalDate startDate;
    private LocalDate endDate;

    public CustomizeReportDto() {
    }

    public CustomizeReportDto(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "CustomizeReportDto{" +
                "startDate=" + startDate.toString() +
                ", endDate=" + endDate.toString() +
                '}';
    }
}
