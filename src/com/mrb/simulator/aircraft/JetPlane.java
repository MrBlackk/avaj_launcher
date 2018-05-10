package com.mrb.simulator.aircraft;

import com.mrb.simulator.WeatherTower;
import com.mrb.weather.Weather;

public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		String weather = weatherTower.getWeather(coordinates);
		if (weather.equals(Weather.SUN.name()))
			updateCoordinates("This is hot. Shit!", 0, 10, 2);
		else if (weather.equals(Weather.RAIN.name()))
			updateCoordinates("It's raining. Better watch out for lightings.", 0, 5, 0);
		else if (weather.equals(Weather.FOG.name()))
			updateCoordinates("It's fog! #@)₴?$0", 0, 1, 0);
		else if (weather.equals(Weather.SNOW.name()))
			updateCoordinates("OMG! Winter is coming!", 0, 0, -7);

		if (coordinates.getHeight() <= 0) {
			System.out.println(this + " landing");
			weatherTower.unregister(this);
		}
	}

	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
	}

	@Override
	public String toString() {
		return "JetPlane#" + this.name + "(" + this.id +")";
	}
}