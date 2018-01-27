package com.disease;

import java.util.*;

public class UserDisease extends Disease{
	private float percentage;
	private float timeExperienced; //figure out unit for this
	private ArrayList<Symptom> coreSymptoms;
	private ArrayList<Symptom> otherSymptoms;
	private boolean haveCoreSymptoms;
	
	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	public void addCoreSymptom(Symptom s){
		coreSymptoms.add(s);
	}
	
	public void removeCoreSymptom(Symptom s){
		coreSymptoms.remove(s);
	}
	
	public void addOtherSymptom(Symptom s){
		otherSymptoms.add(s);
	}
	
	public void removeOtherSymptom(Symptom s){
		otherSymptoms.remove(s);
	}
	
	public float getTimeExperienced() {
		return timeExperienced;
	}
	
	public void setTimeExperienced(float timeExperienced) {
		this.timeExperienced = timeExperienced;
	}
}
