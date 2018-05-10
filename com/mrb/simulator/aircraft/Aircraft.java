package com.mrb.simulator.aircraft;

import com.mrb.simulator.Log;

public abstract class Aircraft implements Comparable<Aircraft> {

	protected long id;
	protected String name;
	protected Coordinates coordinates;
	private static long idCounter = 1;

	protected Aircraft(String name, Coordinates coordinates) {
		this.name = name;
		this.coordinates = coordinates;
		this.id = nextId();
	}

	private long nextId() {
		return idCounter++;
	}

	protected void updateCoordinates(String message, int addLongitude, int addLatitude, int addHeight) {
		Log.info(this + ": " + message);
		if (coordinates.getHeight() + addHeight <= 0) {
			Log.info(this + " landing");
			addHeight = -coordinates.getHeight();
		} else if (coordinates.getHeight() + addHeight > 100) {
			Log.info(this + " too high...");
			addHeight = 100 - coordinates.getHeight();
		}
		coordinates = new Coordinates(
					coordinates.getLongitude() + addLongitude,
					coordinates.getLatitude() + addLatitude,
					coordinates.getHeight() + addHeight);
	}

	@Override
	public int compareTo(Aircraft o) {
		return this.name.compareTo(o.name);
	}
} 