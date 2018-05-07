package com.mrb.simulator;

import java.util.ArrayList;
import java.util.List;
import com.mrb.simulator.aircraft.Flyable;

public abstract class Tower {

	private List<Flyable> observers = new ArrayList<Flyable>();

	public void register(Flyable flyable) {
		observers.add(flyable);
	}

	public void	unregister(Flyable flyable) {
		observers.remove(flyable);
	}

	protected void conditionsChanged() {
		for (Flyable flyable: observers)
			flyable.updateConditions();
	}
}