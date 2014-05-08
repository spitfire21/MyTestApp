package com.example.test.dummy;

/*
* Copyright 2011 Lauri Nevala.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.example.test.ItemListFragment;
import com.example.test.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;


public class CalendarView extends Activity {

	public Calendar month;
	public CalendarAdapter adapter;
	public Handler handler;
	public ArrayList<String> items; // container to store some random calendar items

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.calendar);
	    month = Calendar.getInstance();
	    onNewIntent(getIntent());

	    items = new ArrayList<String>();
	    adapter = new CalendarAdapter(this, month);

	    GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(adapter);

	    handler = new Handler();
	    handler.post(calendarUpdater);

	    TextView title  = (TextView) findViewById(R.id.title);
	    title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

	    TextView previous  = (TextView) findViewById(R.id.previous);
	    previous.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(month.get(Calendar.MONTH)== month.getActualMinimum(Calendar.MONTH)) {				
					month.set((month.get(Calendar.YEAR)-1),month.getActualMaximum(Calendar.MONTH),1);
				} else {
					month.set(Calendar.MONTH,month.get(Calendar.MONTH)-1);
				}
				refreshCalendar();
			}
		});

	    TextView next  = (TextView) findViewById(R.id.next);
	    next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(month.get(Calendar.MONTH)== month.getActualMaximum(Calendar.MONTH)) {				
					month.set((month.get(Calendar.YEAR)+1),month.getActualMinimum(Calendar.MONTH),1);
				} else {
					month.set(Calendar.MONTH,month.get(Calendar.MONTH)+1);
				}
				refreshCalendar();

			}
		});

		gridview.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		    /*	TextView date = (TextView)v.findViewById(R.id.date);
		        if(date instanceof TextView && !date.getText().equals("")) {

		        	Intent intent = new Intent();
		        	String day = date.getText().toString();
		        	if(day.length()==1) {
		        		day = "0"+day;
		        	}
		        	// return chosen date as string format 
		        	intent.putExtra("date", android.text.format.DateFormat.format("yyyy-MM", month)+"-"+day);
		        	setResult(RESULT_OK, intent);
		        	finish();
		        }

		   */ }
		});
	}

	public void refreshCalendar()
	{
		TextView title  = (TextView) findViewById(R.id.title);

		adapter.refreshDays();
		adapter.notifyDataSetChanged();				
		handler.post(calendarUpdater); // generate some random calendar items				

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
	}

	public void onNewIntent(Intent intent) {
		String date = intent.getStringExtra("date");
		String[] dateArr = date.split("-"); // date format is yyyy-mm-dd
		month.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]));
	}

	public Runnable calendarUpdater = new Runnable() {
		DbWorkouts db = new DbWorkouts(ItemListFragment.context);
		List<Workout> workouts = db.getAllContacts();
		
		@Override
		public void run() {
			ArrayList<Integer> ids = new ArrayList<Integer>();
			items.clear();
			int day = month.get(Calendar.DAY_OF_MONTH);
			
			// format random values. You can implement a dedicated class to provide real values
			for(int y= 0; y < workouts.size(); y++){
			for(int i=0;i<31;i++) {
				Random r = new Random();
				//workouts.get(0).getDays().contains(month.get(Calendar.DAY_OF_WEEK)+=i-1%7+"");
				
				if(workouts.get(y).getDays().contains((month.get(Calendar.DAY_OF_WEEK) + i - 2)%7+"")
						&& workouts.get(y).getStart() <= month.getTimeInMillis() && workouts.get(y).getEnd() >= month.getTimeInMillis())
				{
					ids.add(workouts.get(y).getNumber()%6);
					items.add(Integer.toString(i));
					//if(month.get(Calendar.DAY_OF_MONTH) < 30){
					//month.set(Calendar.DAY_OF_MONTH,month.get(Calendar.DAY_OF_MONTH)+1);
					//}
				}
			}
			//month.set(Calendar.DAY_OF_MONTH,day);
			}
			adapter.setID(ids);
			adapter.setItems(items);
			adapter.notifyDataSetChanged();
			//month.set(Calendar.DAY_OF_MONTH,day);
		}
		
	};
}
