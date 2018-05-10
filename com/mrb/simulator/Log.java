package com.mrb.simulator;

import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.FileHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.Date;
import java.util.logging.LogRecord;

public class Log {

	private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void setup() throws IOException {
		FileHandler file = new FileHandler("simulation.txt");
		LOGGER.setUseParentHandlers(false);
		LOGGER.setLevel(Level.INFO);

		file.setFormatter(new SimpleFormatter() {
			private static final String format = "%3$s%n";

			@Override
			public synchronized String format(LogRecord lr) {
				return String.format(format,
						new Date(lr.getMillis()),
						lr.getLevel().getLocalizedName(),
						lr.getMessage()
				);
			}
		});
		LOGGER.addHandler(file);

	}

	public static void info(String message) {
		LOGGER.info(message);
	}

}