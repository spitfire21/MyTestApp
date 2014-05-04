package com.example.test.dummy;

public class Lift {
	public String number;
	public String id;
	public int reps;
	public int sets;
	public String Description;
	public int[] weight;
	public String wrkNm;
	public Lift(String Number, String id, String Description, String wrkName, int reps, int sets) {
		this.number = number;	
		int numDays = 0;
		this.id = id;
		this.reps = reps;
		this.sets = sets;
		this.Description = Description;
		this.wrkNm = wrkName;
		/*
		for(int i = 0; i < weekdays.length(); i++){
			if(weekdays.charAt(i) == ' '){
				numDays++;
			}
		}
		this.weight = new int[numDays*numWeeks];
		*/
	}
	public Lift(String id, String Description, String wrkName) {
	
		int numDays = 0;
		this.id = id;
		this.reps = reps;
		this.sets = sets;
		this.Description = Description;
		this.wrkNm = wrkName;
		/*
		for(int i = 0; i < weekdays.length(); i++){
			if(weekdays.charAt(i) == ' '){
				numDays++;
			}
		}
		this.weight = new int[numDays*numWeeks];
		*/
	}
	

	@Override
	public String toString() {
		return id + " \n"+ sets + " x " + reps ;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int[] getWeight() {
		return weight;
	}

	public void setWeight(int[] weight) {
		this.weight = weight;
	}

	public String getWrkNm() {
		return wrkNm;
	}

	public void setWrkNm(String wrkNm) {
		this.wrkNm = wrkNm;
	}

}
