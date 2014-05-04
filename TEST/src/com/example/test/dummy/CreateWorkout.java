package com.example.test.dummy;


import java.util.ArrayList;

import com.example.test.ItemListActivity;
import com.example.test.ItemListFragment;
import com.example.test.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateWorkout extends Activity {

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createworkout);
        
        
        
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Add Event");
        dialog.setContentView(R.layout.add_even_on);
        Button datePicker = (Button) dialog.findViewById(R.id.datePicker);
        final DialogFragment dateFrag = new MyDatePicker();
            datePicker.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                        dateFrag.show(getSupportFragmentManager(), "datePicker");               
                    }
                });
        dialog.show();
        
        
        
        final ArrayList<Integer> selectedItems = new ArrayList<Integer>();
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateWorkout.this);
        builder.setTitle("Choose your days");
        final String[] items = new String [7];
        items[0] = "Sunday";
        items[1] = "Monday";
        items[2] = "Tuesday";
        items[3] = "Wednesday";
        items[4] = "Thursday";
        items[5] = "Friday";
        items[6] = "Saturday";
        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {

        	@Override
        	public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        	if (isChecked){
        	selectedItems.add(which);
        	} else if (selectedItems.contains(which)){
        	selectedItems.remove(Integer.valueOf(which));
        	}

        	}
        	});
        	
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

        	@Override
        	public void onClick(DialogInterface dialog, int which) {
        	for(int i=0; i<selectedItems.size(); i++){
        	Toast toast = Toast.makeText(getApplicationContext(), "Selected: " + items[(Integer) selectedItems.get(i)], Toast.LENGTH_SHORT);
        	toast.show();
        	}
        	}
        	});
        builder.create();
        builder.show();
        
        Button next = (Button) findViewById(R.id.Save);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	String text1 = ((EditText) getWindow().getDecorView().findViewById(R.id.editWorkoutName)).getText().toString();
            	String text2 = "";
            	for(int i=0; i<selectedItems.size(); i++){
            		text2 = text2 + " " +(Integer) selectedItems.get(i);
            	
            	}
            	DbWorkouts db = new DbWorkouts(view.getContext());
            	db.addWorkout(new Workout(db.getAllContacts().size(),
            			text1,
            			text2));
            	
            	ItemListFragment.adb.notifyDataSetChanged();
                   
                
                
            	Intent intent = new Intent(getApplicationContext(), ItemListActivity.class);
				startActivity(intent);
            	
            			
            }

        });
    }
 }
