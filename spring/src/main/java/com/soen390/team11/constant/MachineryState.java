package com.soen390.team11.constant;

/**
 * Describes the status of the product machinery
 */
public enum MachineryState {
    UNASSIGNED, READY, RUNNING, PAUSED;

    /**
     * Verifies if the state to-be can be achieved from an initial state
     *
     * @param initialState The initial state
     * @param endState The end state
     * @return True if the next state of the machine is the correct one that can be transitioned from the initial state
     */
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
