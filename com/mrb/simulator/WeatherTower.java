package com.mrb.simulator;

import com.mrb.weather.WeatherProvider;
import com.mrb.simulator.aircraft.Coordinates;

public class WeatherTower extends Tower {

	public String getWeather(Coordinates coordinates) {
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}

	void changeWeather() {
		conditionsChanged();
	}
}