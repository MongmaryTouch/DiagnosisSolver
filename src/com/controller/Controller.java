package com.controller;

import java.util.*;
import com.disease.*;
import com.personal.*;

public class Controller {
	
	public static void main(String[] args)
	{	
		try {
			User user = new User("Joe", 'M');
			
			//TODO get list of symptoms from website
			
			//add current symptoms to question map
			//hardcoded for testing
			user.addChangeQuestion(new Symptom("headache"), true);
			user.addChangeQuestion(new Symptom("nausea"), true);
			
			//loop through symptom list
			ArrayList<Disease> allDiseases = new ArrayList<Disease>();
			for(int i = 0; i < user.getQuestionMap().size(); i++){
				//add disease to list of diseases
				Disease d = new Disease(); //TODO get this from the database
				allDiseases.add(d);
			}
			
			//calculate frequency of diseases in list
			ArrayList<Integer> freq = getFreqArray(allDiseases);
			
			//loop through diseases
			for(int i = 0; i < freq.size(); i++){
				//core checklist
				//send map<Symptom, Boolean> to front end
				//get back map<Symptom, Boolean> with adjusted bool values
				//get percentages for present symptoms from database
				//correct core symptoms value
				//adjust percentage
				//add answers
				//other checklist
				//send map<Symptom, Boolean> to front end
				//get back map<Symptom, Boolean> with adjusted bool values
				//get percentages for present symptoms from database
				//adjust percentage 
				//add answers
			}
			
			//update possible conditions based on percentage
			
			//display possible conditions with percentage, lists of symptoms etc
			
			//display comorbidity with percentages 
				//compare comorbidities with question map
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static <T> ArrayList<Integer> getFreqArray(ArrayList<T> c){
		ArrayList<Integer> freq = new ArrayList<Integer>();
		int count;
		
		for(int i = 0; i < c.size(); i++){
			count = 1;
			for(int j = i + 1; j < c.size(); j++){
				if(c.get(i).equals(c.get(j))){
					count++;
					if(freq.get(j) == 0)
						freq.set(j, (i * -1) -1);
				}
			}
			
			if(freq.get(i) == 0)
				freq.set(i, count);
		}
		
		return freq;
	}
}
