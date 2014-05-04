package com.example.test.dummy;

import java.util.ArrayList;
import java.util.List;
import com.jjoe64.graphview.GraphView.GraphViewData;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.example.test.ItemDetailActivity;
import com.example.test.ItemDetailFragment;
import com.example.test.ItemListActivity;
import com.example.test.ItemListFragment;
import com.example.test.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

public class DetailLift extends Activity{
	private TextSwitcher mSwitcher;
	private List<Lift> mLift;
	 private List<Workout> mItem;
private String liftName, test;
private DbWorkouts db;
private TextView asd;
private List<Weight> listWeights = new ArrayList<Weight>();


int index= 0;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.detail_lift);
   
     // init example series data

        test = "";
      TextView asd =   (TextView) findViewById(R.id.detailLiftText);
      asd.setTextSize(36);
      db = new DbWorkouts(ItemListFragment.context);
      mLift = db.getAllLifts();
      
  	
      for(int i = 0; i < mLift.size(); i++){
    	  if((mLift.get(i).getId() + " \n"+ mLift.get(i).getSets() + " x " + mLift.get(i).getReps()).equals(ItemDetailFragment.visibleString)){
    		  
    		  test = mLift.get(i).getId() + " \n" + mLift.get(i).getDescription() + " \n" + 
    				  mLift.get(i).getSets() + " x " + mLift.get(i).getReps();
    		  asd.setText(test);
    	  }
    	  
      }
      List<Weight> tempList = new ArrayList<Weight>();
      tempList = db.getAllWeights();
      
      for(int y = 0; y < tempList.size(); y++){
    		if(tempList.get(y).getName().equals(test.split(" \n")[0])){
    			listWeights.add(tempList.get(y));
    		}
    		
        }
      
      

      
      
      mSwitcher =  (TextSwitcher) findViewById(R.id.switcher1);
      
 	   
      mSwitcher.setFactory(new ViewFactory() {

    	
          public View makeView() {
              // TODO Auto-generated method stub
              // create new textView and set the properties like clolr, size etc
              TextView myText = new TextView(ItemListFragment.context);
              myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
              myText.setTextSize(18);
              if(listWeights.size() > 1){
              if(listWeights.get(listWeights.size()-2).getWeightAmount() > listWeights.get(listWeights.size()-1).getWeightAmount()){
                  myText.setTextColor(Color.RED);
               }
         	      else{
         	    	  myText.setTextColor(Color.GREEN);
         	      }
              }
              else{
            	  myText.setTextColor(Color.GREEN);
              }
              
              return myText;
          }
      });

      // Declare the in and out animations and initialize them 
      Animation in = AnimationUtils.loadAnimation(ItemListFragment.context,android.R.anim.slide_in_left);
      Animation out = AnimationUtils.loadAnimation(ItemListFragment.context,android.R.anim.slide_out_right);
      
      
      // set the animation type of textSwitcher
      mSwitcher.setInAnimation(in);
      mSwitcher.setOutAnimation(out);
      
      Button nextButton = (Button) findViewById(R.id.next);
     
			
      Button addWeight = (Button) findViewById(R.id.addWeight);
     
      TextView t1 = (TextView) mSwitcher.getChildAt(0); 
      index = listWeights.size();	
      if(listWeights.size() > 1){
      if(listWeights.get(listWeights.size()-2).getWeightAmount() > listWeights.get(listWeights.size()-1).getWeightAmount()){
         ((TextView) mSwitcher.getChildAt(0)).setTextColor(Color.RED);
      }
	      else{
	    	  ((TextView) mSwitcher.getChildAt(0)).setTextColor(Color.GREEN);
	      }
	}
  	if(listWeights.size() > 0){
        mSwitcher.setText(String.valueOf(listWeights.get(listWeights.size()-1).getWeightAmount()));
        }
        index--;
		
	}
	
	public void buttonClick(View v) {
		Context context = ItemListFragment.context;
		liftName = test;
		final TextView t1 = (TextView) mSwitcher.getChildAt(0); 
		switch(v.getId()){
	 case R.id.next:
     	
     	if(index > 1){
     	      if(listWeights.get(index-2).getWeightAmount() > listWeights.get(index-1).getWeightAmount()){
     	         t1.setTextColor(Color.RED);
     	          }
     	      else{
     	    	 t1.setTextColor(Color.GREEN);
     	      }
     	}
		db = new DbWorkouts(context);
		mLift = db.getAllLifts();
	    mItem = db.getAllContacts();
	    if(listWeights.size() > 0 && index > 0){
		mSwitcher.setText(String.valueOf(listWeights.get(index-1).getWeightAmount()));
		index--;
	    }
       break;
	
     case R.id.addWeight:
     	final EditText input = new EditText(v.getContext());
     	AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
     	alert.setTitle("Weight");
     	alert.setMessage("Add Weight");

     	// Set an EditText view to get user input 
     	
     	alert.setView(input);

     	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
     	public void onClick(DialogInterface dialog, int whichButton) {
     		String temp = null;
     			for(int y = 0; y < mLift.size(); y++){
     				if(mLift.get(y).getId().equals(test.split(" \n")[0])){
     					
     					liftName = mLift.get(y).getId();
     					temp = mLift.get(y).getWrkNm();
     			}
     		}
     	  String value = input.getText().toString();
     	  db.addWeight(new Weight(db.getAllWeights().size()+"", temp, liftName, Integer.parseInt(input.getText().toString())));
     	 listWeights.add(new Weight(db.getAllWeights().size()+"", temp, liftName, Integer.parseInt(input.getText().toString())));
       
         index = listWeights.size();
         if(index > 1){
    	      if(listWeights.get(listWeights.size()-2).getWeightAmount() > listWeights.get(listWeights.size()-1).getWeightAmount()){
    	         t1.setTextColor(Color.RED);
    	          }
    	      else{
    	    	 t1.setTextColor(Color.GREEN);
    	      }
    	}
     	if(listWeights.size() > 0){
           mSwitcher.setText(String.valueOf(listWeights.get(listWeights.size()-1).getWeightAmount()));
           }
           index--;
     	  }
     	});

     	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
     	  public void onClick(DialogInterface dialog, int whichButton) {
     	    // Canceled.
     	  }
     	});

     	alert.show();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater=getMenuInflater();
	    inflater.inflate(R.layout.menu2, menu);
	    return super.onCreateOptionsMenu(menu);

	}
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.graph:
///////////////////////////////////////////////


Dialog dialog = new Dialog(this);
dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


dialog.setContentView(R.layout.graph);
dialog.show();
dialog.setCanceledOnTouchOutside(true);
GraphViewData[] data = new GraphViewData[listWeights.size()];


for(int i =0; i<listWeights.size(); i++ ){
data[i] = new GraphViewData(i, listWeights.get(i).weightAmount);
}
GraphViewSeries weightGraph = new GraphViewSeries("Weight", null, data);

GraphView graphView = new LineGraphView(
this // context
, "WeightGraph" // heading
);
graphView.addSeries(weightGraph); // data
//set view port, start=2, size=40
graphView.setViewPort(2, 40);
graphView.setScrollable(true);
//optional - activate scaling / zooming
graphView.setScalable(true);
graphView.getGraphViewStyle().setGridColor(Color.BLUE);
graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
graphView.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
graphView.getGraphViewStyle().setTextSize(24);
LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.graph);
layout.addView(graphView);

//////////////////////////////////////////////
break;
	  

	   
	  } 
	    return true;
	}
	
}
