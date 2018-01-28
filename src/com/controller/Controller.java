package com.controller;

import java.sql.DriverManager;

import com.database.SymptomDatabase;
import com.mysql.jdbc.Connection;

public class Controller {
	public static void main(String[] args) throws Exception
    {
		Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/DiagnosisSql?autoReconnect=true&useSSL=false", "root", "admin");
		SymptomDatabase obj = new SymptomDatabase(conn, "Symptom");
		String key1 = "gastrointestinal symptoms";
		String key2 = "CL";
		
		obj.insert(key1, key2);
		System.out.println(key1 + ":" + key2);
    }
}
