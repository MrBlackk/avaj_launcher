package com.mrb.simulator;

import java.util.Queue;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

import com.mrb.simulator.aircraft.Flyable;

import com.mrb.simulator.Log;

public abstract class Tower {

	private Queue<Flyable> observers = new LinkedBlockingQueue<Flyable>();

	public void register(Flyable flyable) {
		Log.info("Tower says: " + flyable + " registered to weather tower.");
		observers.add(flyable);
	}

	public void unregister(Flyable flyable) {
		Iterator<Flyable> iter = observers.iterator();
		while (iter.hasNext()) {
			if (iter.next() == flyable) {
				Log.info("Tower says: " + flyable + " unregistered from weather tower.");
				iter.remove();
			}
		}
	}

	protected void conditionsChanged() {
		for (Flyable observer : observers)
			observer.updateConditions();
	}
}