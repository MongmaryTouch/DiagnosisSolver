package com.disease;

public class Symptom {
	private String name; //for database lookup
	
	public Symptom(){
		name = "";
	}
	
	public Symptom(String n){
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
