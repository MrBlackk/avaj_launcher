package com.mrb.simulator.aircraft;

public abstract class AircraftFactory {

	public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
		Coordinates coord = new Coordinates(longitude, latitude, height);
		switch (type) {
			case "Helicopter":
				return new Helicopter(name, coord);
			case "Baloon":
				return new Helicopter(name, coord);
			case "JetPlane":
				return new Helicopter(name, coord);
			default:
				throw new IllegalArgumentException("Invalid aircraft type: " + type);
		}
	}
}