package com.example.test.dummy;

public class Weight {
public String id;
public String name;
public String wrkoutName;
public int weightAmount;

public Weight(String id, String wrkoutName, String Name, int weightamnt){
	this.id = id;
	this.name = Name;
	this.wrkoutName = wrkoutName;
	this.weightAmount = weightamnt;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getWrkoutName() {
	return wrkoutName;
}

public void setWrkoutName(String wrkoutName) {
	this.wrkoutName = wrkoutName;
}

public int getWeightAmount() {
	return weightAmount;
}

public void setWeightAmount(int weightAmount) {
	this.weightAmount = weightAmount;
}
}
