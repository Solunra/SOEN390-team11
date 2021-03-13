package com.soen390.team11.constant;

public enum MachineryState {
    UNASSIGNED, READY, RUNNING, PAUSED;

    public static boolean validateStateTransition(MachineryState initialState, MachineryState endState) {
        return switch (initialState) {
            case UNASSIGNED -> endState.equals(READY) || endState.equals(UNASSIGNED);
            case READY -> switch (endState) {
                case UNASSIGNED, READY, RUNNING -> true;
                default -> false;
            };
            case RUNNING -> true;
            case PAUSED -> switch (endState) {
                case UNASSIGNED, RUNNING, PAUSED -> true;
                default -> false;
            };
        };
    }
}
