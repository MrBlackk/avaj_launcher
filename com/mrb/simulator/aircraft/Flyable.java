package com.mrb.simulator.aircraft;

import com.mrb.simulator.WeatherTower;

public interface Flyable {

	public void updateConditions();

	public void registerTower(WeatherTower weatherTower);
}