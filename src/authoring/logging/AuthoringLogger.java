package authoring.logging;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class AuthoringLogger {
	private static final String LOGGING_TEXT_FILE_NAME = "Logging.txt";
	private static final String LOGGING_HTML_FILE_NAME = "Logging.html";
	
	private AuthoringLogger() {
		//
	}
	
	/**
	 * Sets up the Authoring Logger
	 * @throws IOException
	 */
	static public void setup() throws IOException{
		Logger globalLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		if(handlers[0] instanceof ConsoleHandler) {
			rootLogger.removeHandler(handlers[0]);
		}
		
		com.sun.media.jfxmedia.logging.Logger.setLevel(Level.INFO.intValue());
		FileHandler fileTxt = new FileHandler(LOGGING_TEXT_FILE_NAME);
		FileHandler fileHTML = new FileHandler(LOGGING_HTML_FILE_NAME);
		
		SimpleFormatter simpleFormatter = new SimpleFormatter();
		fileTxt.setFormatter(simpleFormatter);
		globalLogger.addHandler(fileTxt);
		
		Formatter formatterHTML = new HtmlFormatter();
		fileHTML.setFormatter(formatterHTML);
		globalLogger.addHandler(fileHTML);
	}
}
