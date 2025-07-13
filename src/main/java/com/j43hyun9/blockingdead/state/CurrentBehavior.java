package com.j43hyun9.blockingdead.state;

@FunctionalInterface
public interface CurrentBehavior {
    public static final String MOVE = "MOVE";
    public static final String STOP = "STOP";

    public String getAction();
}
