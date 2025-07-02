package com.j43hyun9.blockingdead.concept;

public interface StateHandler {
    public void register();
    // 등록이 있다면 등록 해제도 당연히 필요하지 않을까?
    public void unregister();
}
