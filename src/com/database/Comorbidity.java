package com.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Comorbidity {
	private String table;
	private Connection conn;

	public Comorbidity(Connection conn, String table) {
		this.table = table;
		this.conn = conn;
	}

	// traverse through database to search for keys pair
	private Boolean searchRow(String keyword1, String keyword2) {
		Boolean pairExist = false;
		ResultSet resultSet = null;

		String query = String.format("SELECT EXISTS(SELECT Disease, Comorbidity FROM %s "
				+ "WHERE %s.Disease LIKE ? and %s.Comorbidity LIKE ? LIMIT 1)", this.table, this.table, this.table);

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

	public void insert(String disease, String comorbidity) {

		String query = String.format("INSERT INTO %s (Disease, Comorbidity)"
				+ " VALUES (?, ?)", this.table);

		Boolean pairExist = searchRow(disease, comorbidity);

		if(pairExist) {   
			return;
		}
		addToDB(disease, comorbidity, query); 

	}

	public List<String> getDisease(String keyword) {
		ResultSet resultSet = null;
		List<String> relatedList = new ArrayList<String>();

		String query = String.format("SELECT %s.Comorbidity FROM %s WHERE %s.Disease = ?", this.table, this.table, this.table);

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
	
	public List<String> getComorbidity(String keyword) {
		ResultSet resultSet = null;
		List<String> relatedList = new ArrayList<String>();

		String query = String.format("SELECT %s.Disease FROM %s WHERE %s.Comorbidity = ?", this.table, this.table, this.table);

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
}
