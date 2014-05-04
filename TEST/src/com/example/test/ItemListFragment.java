package com.example.test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.dummy.DbWorkouts;

import com.example.test.dummy.CalendarViewSampleActivity;
import com.example.test.dummy.CreateWorkout;
import com.example.test.dummy.Lift;
import com.example.test.dummy.Workout;

/**
 * A list fragment representing a list of Items. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * {@link ItemDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ItemListFragment extends ListFragment {
	public static Context context;
	private boolean remove;
	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	/**
	 * The fragment's current callback object, which is notified of list item
	 * clicks.
	 */
	private Callbacks mCallbacks = sDummyCallbacks;

	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int mActivatedPosition = ListView.INVALID_POSITION;

	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public static ArrayAdapter adb;
	public static int globalPosition;
	public interface Callbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onItemSelected(String id);
	}

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id) {
		}
	};

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DbWorkouts db = new DbWorkouts(context);
		setHasOptionsMenu(true);
		remove = false;
		// TODO: replace with a real list adapter.
		
		adb= new ArrayAdapter<Workout>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, db.getAllContacts());
		setListAdapter(adb);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	
		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		 context = activity.getApplicationContext();
		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		DbWorkouts db = new DbWorkouts(context);
		if(remove == false){
		super.onListItemClick(listView, view, position, id);
		
		globalPosition = position;
		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.
		mCallbacks.onItemSelected(db.getAllContacts().get(position).id);
		}
		else{
			remove = false;

			db.delete(db.getAllContacts().get(position));
			
			adb= new ArrayAdapter<Workout>(getActivity(),
					android.R.layout.simple_list_item_activated_1,
					android.R.id.text1, db.getAllContacts());
			setListAdapter(adb);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}
	@Override
	public void onCreateOptionsMenu(
	      Menu menu, MenuInflater inflater) {
	   inflater.inflate(R.layout.menu, menu);
	   
	}
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // action with ID action_refresh was selected
	    case R.id.delete:
	    	remove = true;
	      Toast.makeText(context, "Select An Item to Remove", Toast.LENGTH_SHORT)
	          .show();
	      break;
	    // action with ID action_settings was selected
	  
	    case R.id.calendar:
       	 
	    	Intent intent = new Intent(context, CalendarViewSampleActivity.class);
			startActivityForResult(intent, 0);
       	 break;
	   
	  } 
	    return true;
	}
	public class MyTask extends AsyncTask<Void,Void,String>{
	    @Override
	    protected String doInBackground(Void... voids) {
	        //-- put get data code here --
	        //-- if this takes too much time, repeatedly check "isCancelled()", and exit if its true--
	        return "the string result";
	    }
	}
	public void ShowDialog(Context c){
	    Dialog d = new Dialog(c);
	  
	 
	    
	    
	    
	

	    //--setup the task to update text--
	    final MyTask w = new MyTask(){
	        @Override
	        protected void onPostExecute(String s) {
	            super.onPostExecute(s);
	            
	        }
	    };

	    //--setup the dialog to run task when shown--
	    d.setOnShowListener(new DialogInterface.OnShowListener() {
	        @Override
	        public void onShow(DialogInterface dialogInterface) {
	            w.execute();
	        }
	    });

	    //--setup the dialog to kill task if its dismissed--
	    d.setOnDismissListener(new DialogInterface.OnDismissListener() {
	        @Override
	        public void onDismiss(DialogInterface dialogInterface) {
	            w.cancel(true);
	        }
	    });


	    //-- show the dialog--
	    d.show();
	}
}
