package com.soen390.team11.dto;

/**
 * data summary dto for the summary in data tap
 * consist of action
 * and value
 */
public class DataSummaryDto {
    private String messageAction;
    private String value;

    public DataSummaryDto(String messageAction, String value) {
        this.messageAction = messageAction;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getMessageAction() {
        return messageAction;
    }
}
