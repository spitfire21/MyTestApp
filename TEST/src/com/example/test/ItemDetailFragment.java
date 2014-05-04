package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.example.test.dummy.CreateLift;
import com.example.test.dummy.CreateWorkout;
import com.example.test.dummy.DbWorkouts;
import com.example.test.dummy.DetailLift;

import com.example.test.dummy.Lift;
import com.example.test.dummy.Workout;

/**
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link ItemListActivity} in two-pane mode (on tablets) or a
 * {@link ItemDetailActivity} on handsets.
 */
public class ItemDetailFragment extends Fragment{
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private Context context = ItemListFragment.context;
	private List<Workout> mItem;
	private List<Lift> mLift;
    private Button createLift;
    private TextSwitcher mSwitcher;
    private ArrayAdapter simpleAdpt;
    public static String visibleString;
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			//mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
				//	ARG_ITEM_ID));
			
		}
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		List<Lift> liftList = new ArrayList<Lift>();
		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);
		
		// Show the dummy content as text in a TextView.
		final Context detailContext = ItemDetailActivity.detailContext;
		DbWorkouts db = new DbWorkouts(context);
		
		
		
		
		mLift = db.getAllLifts();
		
		mItem = db.getAllContacts();
	for(int i = 0; i < mLift.size(); i++){
		
		if(mItem.get(ItemListFragment.globalPosition).getId().equals(mLift.get(i).getWrkNm())){
			
					liftList.add(mLift.get(i));	
			
		 
		}
	}
	
	ListView lv = (ListView) rootView.findViewById(R.id.listView1);
	 simpleAdpt = new ArrayAdapter<Lift>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, liftList);
	 lv.setAdapter(simpleAdpt);
	 registerForContextMenu(lv);

	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	     public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
	                             long id) {

	         // We know the View is a TextView so we can cast it
	         TextView clickedView = (TextView) view;

	         Toast.makeText(detailContext, "Item with id ["+id+"] - Position ["+position+"] - Lift ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();

	     }
	});
		
				
			
			
	        createLift = (Button) rootView.findViewById(R.id.createlift);
			
		return rootView;
	}
	       
			
	@Override
	public void onCreateOptionsMenu(
	      Menu menu, MenuInflater inflater) {
	   inflater.inflate(R.layout.menu, menu);
	   
	}
	
         
	        
	   
	        @Override
	        public void onCreateContextMenu(ContextMenu menu, View v,
	                ContextMenuInfo menuInfo) {
	        	
	            super.onCreateContextMenu(menu, v, menuInfo);
	          //  AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;
	            AdapterView.AdapterContextMenuInfo info =
	                    (AdapterView.AdapterContextMenuInfo) menuInfo;
	            // We know that each row in the adapter is a Map
	           
	            visibleString = ((TextView) info.targetView).getText().toString();
	            menu.setHeaderTitle("Options for " + ((TextView) info.targetView).getText().toString());
	            menu.add(1, 1, 1, "Details");
	            menu.add(1, 2, 2, "Delete");

	        }
	        @Override
	        public boolean onContextItemSelected(MenuItem item) {

	            if (item.getTitle() == "Delete") {
	                
	            } else if (item.getTitle() == "Details") {
	            	Intent detailIntent = new Intent(context, DetailLift.class);
	    			
	    			startActivity(detailIntent);
	                
	            } else {
	                return false;
	            }
	            return true;

	        }
	        }
	

