package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	//DB接続用定数
	static String DATABASE_NAME = "product_management";
	static String URL = "jdbc:mysql://localhost/" + DATABASE_NAME;
	//DB接続用・ユーザ定数
	static String USER = "root";
	static String PASS = "Taichi1775";

	//データベースに接続
	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(URL, USER, PASS);
	}

}