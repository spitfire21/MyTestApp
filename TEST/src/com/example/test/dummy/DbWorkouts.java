package com.example.test.dummy;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbWorkouts extends SQLiteOpenHelper{

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "liftManagers13";
 
    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    
    private static final String TABLE_WEIGHT = "Weight";
    
    private static final String Weight_Id = "id";
    private static final String Weight_WRKOUTName = "Workout_Name";
    private static final String Weight_Name = "Name";
    private static final String Weight_amount = "weight";
    
    
 // Contacts table name
    private static final String TABLE_LIFTS = "lifts";
 
    // Contacts Table Columns names
    private static final String Lift_ID = "id";
    private static final String Lift_NAME = "name";
    private static final String Lift_PH_NO = "phone_number";
    private static final String Lift_WRK_NO = "work_number";
    private static final String Lift_reps = "Reps";
    private static final String Lift_sets= "Sets";
 
    public DbWorkouts(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        
        String CREATE_LIFTS_TABLE = "CREATE TABLE " + TABLE_LIFTS + "("
                + Lift_ID + " INTEGER PRIMARY KEY," + Lift_NAME + " TEXT,"
                + Lift_PH_NO + " TEXT," + Lift_WRK_NO + " TEXT," + Lift_reps + " INTEGER," + Lift_sets + " INTEGER" + ")";
        db.execSQL(CREATE_LIFTS_TABLE);
        
        String CREATE_WEIGHTS_TABLE = "CREATE TABLE " + TABLE_WEIGHT + "("
                + Weight_Id + " INTEGER PRIMARY KEY," + Weight_WRKOUTName + " TEXT," + Weight_Name + " TEXT,"
                + Weight_amount + " INTEGER" + ")";
        db.execSQL(CREATE_WEIGHTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS + TABLE_LIFTS);
 
        // Create tables again
        onCreate(db);
    }
    public void addLift(Lift lift){
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(Lift_ID, lift.getNumber());
        values.put(Lift_NAME, lift.getId()); // Contact Name
        values.put(Lift_PH_NO, lift.getDescription()); // Contact Phone Number
        values.put(Lift_WRK_NO, lift.getWrkNm());
        values.put(Lift_reps, lift.getReps());
        values.put(Lift_sets, lift.getSets());
        // Inserting Row
        db.insert(TABLE_LIFTS, null, values);
        db.close(); // Closing database connection
    	
    }
    public void addWeight(Weight weight){
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(Weight_amount, weight.getWeightAmount());
        values.put(Weight_WRKOUTName, weight.getWrkoutName());
        values.put(Weight_Name, weight.getName());
        values.put(Weight_Id, weight.getId());
        
        
       
        // Inserting Row
        db.insert(TABLE_WEIGHT, null, values);
        db.close(); // Closing database connection
    	
    }
    public void delete(Workout workout) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(workout.getNumber()) });
        db.close();
    }
    public void addWorkout(Workout workout){
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_ID, workout.getNumber());
        values.put(KEY_NAME, workout.getId()); // Contact Name
        values.put(KEY_PH_NO, workout.getDays()); // Contact Phone Number
     
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    	
    }
    public Weight getWeight(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        Cursor cursor = db.query(TABLE_WEIGHT, new String[] { Weight_Id,
        		Weight_WRKOUTName, Weight_Name, Weight_amount
                }, Weight_Id + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Weight weight = new Weight(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        // return contact
        db.close();
        return weight;
        
    }
    public Lift getLift(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        Cursor cursor = db.query(TABLE_LIFTS, new String[] { Lift_ID,
                Lift_NAME, Lift_PH_NO, Lift_WRK_NO, Lift_reps, Lift_sets
                }, Lift_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Lift lift = new Lift(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5));
        // return contact
        db.close();
        return lift;
    }
    public Workout getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Workout workout = new Workout(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        db.close();
        return workout;
    }
    public List<Workout> getAllContacts() {
        List<Workout> workoutList = new ArrayList<Workout>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Workout workout = new Workout((Integer.parseInt(cursor.getString(0))), (cursor.getString(1))
                		, (cursor.getString(2)));
                
                // Adding contact to list
                workoutList.add(workout);
            } while (cursor.moveToNext());
        }
     
        // return contact list
        db.close();
        return workoutList;
    }
    
    public List<Lift> getAllLifts() {
        List<Lift> liftList = new ArrayList<Lift>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LIFTS;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Lift lift = new Lift((cursor.getString(0)), (cursor.getString(1))
                		, (cursor.getString(2)), (cursor.getString(3)), (cursor.getInt(4)), cursor.getInt(5));
                
                // Adding contact to list
                liftList.add(lift);
            } while (cursor.moveToNext());
        }
     
        // return contact list
        db.close();
        return liftList;
    }
    public List<Weight> getAllWeights() {
        List<Weight> weightList = new ArrayList<Weight>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_WEIGHT;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Weight weight = new Weight((cursor.getString(0)), (cursor.getString(1))
                		, (cursor.getString(2)), (cursor.getInt(3)));
                
                // Adding contact to list
                weightList.add(weight);
            } while (cursor.moveToNext());
        }
     
        // return contact list
        db.close();
        return weightList;
    }


}
