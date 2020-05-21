package com.company.dbmanager;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {

	public static Connection conn;

	public DBManager() {
		conn = null;
	}
	
	public Connection getConnection() throws Exception {
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		//DataSource db = (DataSource) envContext.lookup("jdbc/orcl");
		DataSource db = (DataSource)envContext.lookup("jdbc/jingoldcoast");
		conn = db.getConnection();
		return conn;
	}
	
}
