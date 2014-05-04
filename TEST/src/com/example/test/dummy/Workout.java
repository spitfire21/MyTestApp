package com.example.test.dummy;

import java.util.ArrayList;
import java.util.List;

public class Workout {
public int number;
public String id;
public String Days;
public List<Lift> workout = new ArrayList<Lift>();
public int numWeeks;

public Workout (int number, String id, String days, int numWeeks){
	this.number = number;
	this.id = id;
	this.Days = days;
	this.numWeeks = numWeeks;
}
public Workout (int number, String id, String days){
	this.number = number;
	this.id = id;
	this.Days = days;
}
public void addLift(Lift a){
	
	workout.add(a);
 }
@Override
public String toString() {
	
	return id;
}
public String getId() {
	// TODO Auto-generated method stub
	return this.id;
}
public int getNumber(){
	return this.number;
}
public String getDays(){
	return this.Days;
}

}

