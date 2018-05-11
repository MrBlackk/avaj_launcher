package com.mrb.simulator.aircraft;

import com.mrb.simulator.WeatherTower;
import com.mrb.weather.Weather;

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		String weather = weatherTower.getWeather(coordinates);
		if (weather.equals(Weather.SUN.name()))
			updateCoordinates("Let's enjoy the good weather and take some pics.", 2, 0, 4);
		else if (weather.equals(Weather.RAIN.name()))
			updateCoordinates("Damn you rain! You messed up my baloon.", 0, 0, -5);
		else if (weather.equals(Weather.FOG.name()))
			updateCoordinates("FOG! FOG! WTF!", 0, 0, -3);
		else if (weather.equals(Weather.SNOW.name()))
			updateCoordinates("It's snowing. We're gonna crash.", 0, 0, -15);

		if (coordinates.getHeight() <= 0)
			weatherTower.unregister(this);
	}

	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		if (coordinates.getHeight() > 0)
			this.weatherTower.register(this);
	}

	@Override
	public String toString() {
		return "Baloon#" + this.name + "(" + this.id + ")";
	}
}