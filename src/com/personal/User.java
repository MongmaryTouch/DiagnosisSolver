package com.personal;

import java.io.Serializable;
import java.util.*;
import com.disease.*;

public class User implements Serializable{
	private String name;
	private char gender; //'f' or 'm'
	private Map<Symptom, Boolean> questionMap;
	private ArrayList<UserDisease> possibleConditions;
	private ArrayList<UserDisease> diagnosedConditions;
	private ArrayList<Symptom> possibleSymptoms;
	
	public User(){
		name = "";
		gender = 'x';
		questionMap = new HashMap<Symptom, Boolean>();
		possibleConditions = new ArrayList<UserDisease>();
		diagnosedConditions = new ArrayList<UserDisease>();
	}
	
	public User(String n, char g) throws Exception{
		name = n;
		if((g == 'f' || g == 'F') && (g == 'm' || g == 'M'))
			gender = g;
		else
			throw new Exception("Gender must be F or M");
		
		questionMap = new HashMap<Symptom, Boolean>();
		possibleConditions = new ArrayList<UserDisease>();
		diagnosedConditions = new ArrayList<UserDisease>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public char getGender() {
		return gender;
	}
	
	public void setGender(char gender) { //TODO add check
		this.gender = gender;
	}
	
	public void addChangeQuestion(Symptom s, boolean a){ 
		questionMap.put(s, a);
		if(possibleSymptoms.contains(s))
			possibleSymptoms.remove(s);
	}
	
	public void addPossibleCondition(UserDisease d){
		possibleConditions.add(d);
	}
	
	public void removePossibleCondition(UserDisease d){
		possibleConditions.remove(d);
	}
	
	public void addDiagnosedCondition(UserDisease d){
		diagnosedConditions.add(d);
	}
	
	public void removeDiagnosedCondition(UserDisease d){
		diagnosedConditions.remove(d);
	}
	
	public void addPossibleSymmptom(Symptom s){
		possibleSymptoms.add(s);
	}
	
	public void removePossibleSymptom(Symptom s){
		possibleSymptoms.remove(s);
	}
	
	public Map<Symptom, Boolean> getQuestionMap() {
		return questionMap;
	}

	public void setQuestionMap(Map<Symptom, Boolean> questionMap) {
		this.questionMap = questionMap;
	}
	
	public ArrayList<UserDisease> getCoMorbidities(){
		ArrayList<UserDisease> co = new ArrayList<UserDisease>();
		
		//access database?
		
		return co;
	}
}
