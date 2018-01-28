package com.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class SymptomDatabase {
	private String table;
	private Connection conn;

	public SymptomDatabase(Connection conn, String table) {
		this.table = table;
		this.conn = conn;
	}

	// traverse through database to search for keys pair
	private Boolean searchRow(String keyword1, String keyword2) {
		Boolean pairExist = false;
		ResultSet resultSet = null;

		String query = String.format("SELECT EXISTS(SELECT Symptom, Disease FROM %s "
				+ "WHERE %s.Symptom LIKE ? and %s.Disease LIKE ? LIMIT 1)", this.table, this.table, this.table);

		try {
			PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
			preparedStmt.setString(1, keyword1);
			preparedStmt.setString(2, keyword2);

			resultSet = preparedStmt.executeQuery();
			resultSet.next();
			pairExist = resultSet.getBoolean(1);

			preparedStmt.execute();
			preparedStmt.close();
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pairExist;
	}

	// add a row into the database
	private void addToDB(String keyword1, String keyword2, String query) {
		try {
			PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
			preparedStmt.setString (1, keyword1);
			preparedStmt.setString (2, keyword2);
			preparedStmt.execute();
			preparedStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(String symptom, String disease) {

		String query = String.format("INSERT INTO %s (Symptom, Disease)"
				+ " VALUES (?, ?)", this.table);

		Boolean pairExist = searchRow(symptom, disease);

		if(pairExist) {   
			return;
		}
		addToDB(symptom, disease, query); 

	}

	public List<String> getDisease(String keyword) {
		ResultSet resultSet = null;
		List<String> relatedList = new ArrayList<String>();

		String query = String.format("SELECT %s.Disease FROM %s WHERE %s.Symptom = ?", this.table, this.table, this.table);

		try {
			PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
			preparedStmt.setString(1, keyword);
			
			resultSet = preparedStmt.executeQuery();
			while(resultSet.next()) {
				String str = resultSet.getString(1);
				relatedList.add(str);
			}
			resultSet.close();
			preparedStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return relatedList;
	}
	
	public List<String> getSymptom(String keyword) {
		ResultSet resultSet = null;
		List<String> relatedList = new ArrayList<String>();

		String query = String.format("SELECT %s.Symptom FROM %s WHERE %s.Disease = ?", this.table, this.table, this.table);

		try {
			PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
			preparedStmt.setString(1, keyword);
			
			resultSet = preparedStmt.executeQuery();
			while(resultSet.next()) {
				String str = resultSet.getString(1);
				relatedList.add(str);
			}
			resultSet.close();
			preparedStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return relatedList;
	}
	
	public static void main(String[] args) {
		try {
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/DiagnosisSql?autoReconnect=true&useSSL=false", "root", "admin");
			SymptomDatabase obj = new SymptomDatabase(conn, "Symptom");
			//obj.insert("HEY", "YOU2");
			System.out.println(obj.getDisease("HEY"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
