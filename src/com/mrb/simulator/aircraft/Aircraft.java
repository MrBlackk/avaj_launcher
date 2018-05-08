package com.mrb.simulator.aircraft;

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
		System.out.println(this + ": " + message);
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