package com.sonal.kafka.demo.trucklocationtracker.eventlistener;

import com.sonal.kafka.demo.trucklocationtracker.event.TruckLocatedEvent;

public interface TruckLocatedEventListener {

	void onData(TruckLocatedEvent event);
	
    void processComplete();
}
