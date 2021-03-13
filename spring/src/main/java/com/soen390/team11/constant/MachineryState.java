package com.soen390.team11.constant;

public enum MachineryState {
    UNASSIGNED, READY, RUNNING, PAUSED;

    public static boolean validateStateTransition(MachineryState initialState, MachineryState endState) {
        return switch (initialState) {
            case UNASSIGNED -> true;
            case READY -> switch (endState) {
                case UNASSIGNED, READY, RUNNING -> true;
                default -> false;
            };
            case RUNNING -> switch (endState) {
                case READY, RUNNING, PAUSED -> true;
                default -> false;
            };
            case PAUSED -> switch (endState) {
                case RUNNING, PAUSED -> true;
                default -> false;
            };
        };
    }
}
