package com.demo.perf.events;

public class BaseEvent<T> {
    
	public T id;

    public BaseEvent(T id) {
        this.id = id;
    }
    
    public BaseEvent() {}
}
