package org.ds.model.machine;

public enum State {
    FREE,
    BUSY,
    BROKEN;

    public static State getState(int i) { // убрать
        return switch (i) {
            case 0 -> State.FREE;
            case 1 -> State.BUSY;
            case 2 -> State.BROKEN;
            default -> throw new IllegalStateException("Unexpected value: " + i);
        };
    }
}
