package com.example.test.dummy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.test.ItemDetailActivity;
import com.example.test.ItemListActivity;
import com.example.test.ItemListFragment;
import com.example.test.R;

public class CreateLift extends Activity {

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createlift);
        ((NumberPicker) getWindow().getDecorView().findViewById(R.id.repsPicker)).setMinValue(1);
        ((NumberPicker) getWindow().getDecorView().findViewById(R.id.repsPicker)).setMaxValue(100);
        ((NumberPicker) getWindow().getDecorView().findViewById(R.id.setsPicker)).setMinValue(1);
        ((NumberPicker) getWindow().getDecorView().findViewById(R.id.setsPicker)).setMaxValue(100);
        Button next = (Button) findViewById(R.id.SaveLift);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	String text1 = ((EditText) getWindow().getDecorView().findViewById(R.id.editLiftName)).getText().toString();
            	String text2 = ((EditText) getWindow().getDecorView().findViewById(R.id.editLiftDescription)).getText().toString();
            	int reps = ((NumberPicker) getWindow().getDecorView().findViewById(R.id.repsPicker)).getValue();
            	int sets = ((NumberPicker) getWindow().getDecorView().findViewById(R.id.setsPicker)).getValue();
            	DbWorkouts db = new DbWorkouts(view.getContext());
            	db.addLift(new Lift(db.getAllLifts().size()+ "",
            			text1,
            			text2, db.getContact(ItemListFragment.globalPosition).getId(), reps, sets));        
                
                
            	Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class);
				startActivity(intent);
            	
            			
            }

        });
    }
 }

