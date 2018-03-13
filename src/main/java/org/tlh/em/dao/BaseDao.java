package org.tlh.em.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDao {
	
	private static String driver="org.gjt.mm.mysql.Driver";
	private static String url="jdbc:mysql://192.168.64.140:3306/woniuxy";
	private static String user="admin";
	private static String password="admin123";

	public Connection getConnection() throws Exception{
		Class.forName(driver);
		return DriverManager.getConnection(url, user, password);
	}
	
	public void release(Connection connection,Statement statement,ResultSet resultSet) throws Exception{
		if(resultSet!=null){
			resultSet.close();
		}
		if(statement!=null){
			statement.close();
		}
		if(connection!=null){
			connection.close();
		}
	}
	
}
