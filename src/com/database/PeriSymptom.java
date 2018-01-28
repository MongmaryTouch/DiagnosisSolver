package com.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class PeriSymptom {
	private String table;
	private Connection conn;

	public PeriSymptom(Connection conn, String table) {
		this.table = table;
		this.conn = conn;
	}

	// traverse through database to search for keys pair
	private Boolean searchRow(String keyword1, String keyword2) {
		Boolean pairExist = false;
		ResultSet resultSet = null;

		String query = String.format("SELECT EXISTS(SELECT Disease, PeriSymptom FROM %s "
				+ "WHERE %s.Disease LIKE ? and %s.PeriSymptom LIKE ? LIMIT 1)", this.table, this.table, this.table);

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

	public void insert(String disease, String periSymptom) {

		String query = String.format("INSERT INTO %s (Disease, PeriSymptom)"
				+ " VALUES (?, ?)", this.table);

		Boolean pairExist = searchRow(disease, periSymptom);

		if(pairExist) {   
			return;
		}
		addToDB(disease, periSymptom, query); 

	}

	public List<String> getDisease(String keyword) {
		ResultSet resultSet = null;
		List<String> relatedList = new ArrayList<String>();

		String query = String.format("SELECT %s.PeriSymptom FROM %s WHERE %s.Disease = ?", this.table, this.table, this.table);

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

	public List<String> getPeriSymptom(String keyword) {
		ResultSet resultSet = null;
		List<String> relatedList = new ArrayList<String>();

		String query = String.format("SELECT %s.Disease FROM %s WHERE %s.PeriSymptom = ?", this.table, this.table, this.table);

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
