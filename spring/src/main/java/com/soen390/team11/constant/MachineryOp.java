package com.soen390.team11.constant;

/**
 *  Describes the possible operations on a product machinery
 */
public enum MachineryOp {
    LAUNCH, START, PAUSE, CANCEL;

    /**
     * Returns the next status of a product machinery based on the operation
     *
     * @param machineryOp The selected operation on the machine
     * @return The status of the machine
     */
    public static MachineryState getTransitionState(MachineryOp machineryOp) {
        return switch (machineryOp) {
            case LAUNCH -> MachineryState.READY;
            case START -> MachineryState.RUNNING;
            case PAUSE -> MachineryState.PAUSED;
            case CANCEL -> MachineryState.UNASSIGNED;
        };
    }
}
