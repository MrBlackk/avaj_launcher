package com.mrb.simulator;

import com.mrb.simulator.aircraft.AircraftFactory;
import com.mrb.simulator.aircraft.Flyable;

import com.mrb.exception.EmptyStringException;
import com.mrb.exception.SimulationLineFormatException;

import java.util.ArrayList;
import java.util.List;

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
	private static final int MAX_HEIGHT = 100;

	public static void main(String[] arg) {
		BufferedReader br = null;
		FileReader fr = null;

		try {
			if (arg.length != 1)
				throw new IllegalArgumentException("One argument requiered: scenario_file");
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
			System.out.println("File could not found:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Read file error:" + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Number error: " + e.getMessage());
		} catch (EmptyStringException e) {
			System.out.println("Empty String error: " + e.getMessage());
		} catch (SimulationLineFormatException e) {
			System.out.println("Line format error: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Wrong arg: " + e.getMessage());
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
				Log.close();
			} catch (IOException e) {
				System.out.println("Close file error");
			}
		}
	}

	private static int parseNumberSimulations(String line) throws NumberFormatException {
		if (line == null)
			throw new NumberFormatException("File is empty");

		int numSimulations = Integer.parseInt(line);
		if (numSimulations < 0)
			throw new NumberFormatException("Number of simulations is lower than zero, in line '" + line + "'");
		return numSimulations;
	}

	private static Flyable parseFlyable(String line) throws NumberFormatException, EmptyStringException, SimulationLineFormatException {
		String[] splittedLine = line.split(" ");
		if (splittedLine.length != NUM_OF_WORDS)
			throw new SimulationLineFormatException("Wrong line format, in line '" + line + "'");

		if ("".equals(splittedLine[0]))
			throw new EmptyStringException("Type is empty, in line '" + line + "'");
		if ("".equals(splittedLine[1]))
			throw new EmptyStringException("Name is empty, in line '" + line + "'");

		int longitude = Integer.parseInt(splittedLine[2]);
		if (longitude < 0)
			throw new NumberFormatException("Longitude is lower than zero, in line '" + line + "'");

		int latitude = Integer.parseInt(splittedLine[3]);
		if (latitude < 0)
			throw new NumberFormatException("Latitude is lower than zero, in line '" + line + "'");

		int height = Integer.parseInt(splittedLine[4]);
		if (height < 0)
			throw new NumberFormatException("Height is lower than zero, in line '" + line + "'");
		else if (height > MAX_HEIGHT)
			throw new NumberFormatException("Height is bigger than " + MAX_HEIGHT + ", in line '" + line + "'");

		return AircraftFactory.newAircraft(splittedLine[0], splittedLine[1], longitude, latitude, height);
	}
}
