package com.mrb.simulator;

import com.mrb.weather.WeatherProvider;
import com.mrb.simulator.aircraft.AircraftFactory;
import com.mrb.simulator.aircraft.Flyable;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Simulator {

	private static WeatherTower weatherTower;
	private static List<Flyable> flyables = new ArrayList<Flyable>();

	public static void main(String[] arg) {
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(arg[0]);
			br = new BufferedReader(fr);

			String line = br.readLine();

			int numSimulations = Integer.parseInt(line); // java.lang.NumberFormatException and < 0
			while ((line = br.readLine()) != null) {
				Flyable flyable = AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
						Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
						Integer.parseInt(line.split(" ")[4])); //parse all data
				flyables.add(flyable);
			}

			weatherTower = new WeatherTower();
			for (Flyable flyable : flyables)
				flyable.registerTower(weatherTower);
			for (int i = 0; i < numSimulations; i++)
				weatherTower.changeWeather();

		} catch (FileNotFoundException e) {
			System.err.println("File could not found.");
		} catch (IOException e) {
			System.err.println("Read file error.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("No argumenrs were passed.");
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException e) {
				System.err.println("Close file error");
			}
		}
	}
}
