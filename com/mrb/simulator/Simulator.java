package com.mrb.simulator;

import com.mrb.weather.WeatherProvider;
import com.mrb.simulator.aircraft.AircraftFactory;
import com.mrb.simulator.aircraft.Flyable;

import com.mrb.exception.EmptyStringException;
import com.mrb.exception.SimulationLineFormatException;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;

import com.mrb.simulator.Log;

public class Simulator {

	private static WeatherTower weatherTower;
	private static List<Flyable> flyables = new ArrayList<Flyable>();
	private static final int NUM_OF_WORDS = 5;

	public static void main(String[] arg) {
		BufferedReader br = null;
		FileReader fr = null;

		try {
			Log.setup();
			fr = new FileReader(arg[0]);
			br = new BufferedReader(fr);

			String line = br.readLine();
			int numSimulations = parseNumberSimulations(line);
			while ((line = br.readLine()) != null)
				flyables.add(parseFlyable(line));

			weatherTower = new WeatherTower();
			for (Flyable flyable : flyables)
				flyable.registerTower(weatherTower);

			for (int i = 0; i < numSimulations; i++)
				weatherTower.changeWeather();

		} catch (FileNotFoundException e) {
			System.err.println("File could not found:" + e.getMessage());
		} catch (IOException e) {
			System.err.println("Read file error:" + e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("No argumenrs were passed.");
		} catch (NumberFormatException e) {
			System.err.println("Number error: " + e.getMessage());
		} catch (EmptyStringException e) {
			System.err.println("Empty String error: " + e.getMessage());
		} catch (SimulationLineFormatException e) {
			System.err.println("Line format error: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println("Wrong type: " + e.getMessage());
		}
		finally {
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

	private static int parseNumberSimulations(String line) throws NumberFormatException {
		if (line == null)
			throw new NumberFormatException("File is empty");

		int numSimulations = Integer.parseInt(line);
		if (numSimulations < 0)
			throw new NumberFormatException("Number of simulations is lower than zero");
		return numSimulations;
	}

	private static Flyable parseFlyable(String line) throws NumberFormatException, EmptyStringException, SimulationLineFormatException {
		String[] splittedLine = line.split(" ");
		if (splittedLine.length != NUM_OF_WORDS)
			throw new SimulationLineFormatException("Wrong line format");

		if ("".equals(splittedLine[0]))
			throw new EmptyStringException("Type is empty");
		if ("".equals(splittedLine[1]))
			throw new EmptyStringException("Name is empty");

		int longitude = Integer.parseInt(splittedLine[2]);
		if (longitude < 0)
			throw new NumberFormatException("Longitude is lower than zero");

		int latitude = Integer.parseInt(splittedLine[3]);
		if (latitude < 0)
			throw new NumberFormatException("Latitude is lower than zero");

		int height = Integer.parseInt(splittedLine[4]);
		if (height < 0)
			throw new NumberFormatException("Height is lower than zero");
		else if (height > 100)
			throw new NumberFormatException("Height is bigger than 100");

		return AircraftFactory.newAircraft(splittedLine[0], splittedLine[1], longitude, latitude, height);
	}
}
