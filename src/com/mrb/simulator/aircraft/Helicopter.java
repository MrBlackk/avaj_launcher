package com.mrb.simulator.aircraft;

import com.mrb.simulator.WeatherTower;
import com.mrb.weather.Weather;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		String weather = weatherTower.getWeather(coordinates);
		if (weather.equals(Weather.SUN.name()))
			updateCoordinates("This is hot.", 10, 0, 2);
		else if (weather.equals(Weather.RAIN.name()))
			updateCoordinates("It's raining.", 5, 0, 0);
		else if (weather.equals(Weather.FOG.name()))
			updateCoordinates("I see nothing. It's fog!", 1, 0, 0);
		else if (weather.equals(Weather.SNOW.name()))
			updateCoordinates("My rotor is going to freeze!", 0, 0, -12);

		if (coordinates.getHeight() <= 0)
			weatherTower.unregister(this);
	}

	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
	}

	@Override
	public String toString() {
		return "Helicopter#" + this.name + "(" + this.id +")" + coordinates;
	}
}