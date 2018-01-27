package com.disease;

public class Disease implements Comparable<Disease>{
	private String name; //for database lookup

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Disease d) {
		return name.compareTo(d.getName());
	}
}
