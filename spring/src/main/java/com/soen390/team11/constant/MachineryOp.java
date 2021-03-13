package com.soen390.team11.constant;

public enum MachineryOp {
    LAUNCH, START, PAUSE, CANCEL;

    public static MachineryState getTransitionState(MachineryOp machineryOp) {
        return switch (machineryOp) {
            case LAUNCH -> MachineryState.READY;
            case START -> MachineryState.RUNNING;
            case PAUSE -> MachineryState.PAUSED;
            case CANCEL -> MachineryState.UNASSIGNED;
        };
    }
}
