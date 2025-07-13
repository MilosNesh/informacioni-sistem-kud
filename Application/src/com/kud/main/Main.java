package com.kud.main;

import com.kud.connection.ConnectionUtil_HikariCP;
import com.kud.ui_handler.MainUIHandler;

public class Main {
	public static void main(String[] args) {
		
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "error");

		MainUIHandler mainUIHandler = new MainUIHandler();
		try {
			mainUIHandler.handleMainMenu();
			ConnectionUtil_HikariCP.closeDataSource();
		} catch (Exception e) {
			ConnectionUtil_HikariCP.closeDataSource();
		}
	}
}
