package com.ocr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author ngando
 * 
 */
public class DaoFactory {
	
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String HOST = "jdbc:mariadb://localhost:3306";
	private static final String DATABASE = "db_subtitles";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public Connection getConnection() throws SQLException {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(HOST+"/"+DATABASE, USER, PASSWORD);	
	}

}
