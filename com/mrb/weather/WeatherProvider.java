package com.mrb.weather;

import com.mrb.simulator.aircraft.Coordinates;

public class WeatherProvider {
	
	private static WeatherProvider weatherProvider;

	private WeatherProvider() {
	}

	public static WeatherProvider getProvider() {
		if (weatherProvider == null)
			weatherProvider = new WeatherProvider();
		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		int coordSum = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
		int index = coordSum % Weather.values().length;
		return Weather.values()[index].name();
	}
}