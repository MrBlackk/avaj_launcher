package com.mrb.weather;

public class WeatherProvider {
	private static WeatherProvider weatherProvider;

	private WeatherProvider() {
	}

	public static WeatherProvider getProvider() {
		if (weatherProvider == null){
			weatherProvider = new WeatherProvider();
		}
		return weatherProvider;
	}
}