package com.example.options;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class OptionsMainActivity extends ActionBarActivity {
	
	private final String TAG = "OptionsMainActivity";

	private ActionBarDrawerToggle mDrawerToggle;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerListView;
	private String[] mDrawerItems;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawer);

		mTitle = getTitle();
		mDrawerItems = getResources().getStringArray(R.array.NavItems);
		
		mDrawerListView = (ListView) findViewById(R.id.list_slidermenu);
		mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
		
		mDrawerListView.setAdapter(new ArrayAdapter<String>(getSupportActionBar()
				.getThemedContext(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, mDrawerItems));

		// Set up the drawer.
		setUp(R.id.list_slidermenu, (DrawerLayout) findViewById(R.id.drawer_layout));

		if (savedInstanceState == null) {
			// Select either the default item (0) or the last selected item.
			//navigateTo(0);
		}
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Forward the new configuration the drawer toggle component.
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		if (item.getItemId() == R.id.action_example) {
			Toast.makeText(this, "Example action.", Toast.LENGTH_SHORT)
			.show();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Users of this fragment must call this method to set up the navigation
	 * drawer interactions.
	 * 
	 * @param fragmentId
	 *            The android:id of this fragment in its activity's layout.
	 * @param drawerLayout
	 *            The DrawerLayout containing this fragment's UI.
	 */
	public void setUp(int fragmentId, DrawerLayout drawerLayout) {
		
		mDrawerLayout = drawerLayout;

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,	GravityCompat.START);
		// set up the drawer's list view with items and click listener

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the navigation drawer and the action bar app icon.
		mDrawerToggle = new ActionBarDrawerToggle(
				this, /* host Activity */
				mDrawerLayout, /* DrawerLayout object */
				R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
				R.string.navigation_drawer_open, 
				R.string.navigation_drawer_close 
				) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
			}
		};


		// Defer code dependent on restoration of previous instance state.
		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
	}

//	private void navigateTo(int position) {
//
//		// update the main content by replacing fragments
//		Fragment fragment = new Fragment();
//
////		switch (position) {
////		case 0:
////			fragment = OffersFragmentContainer.newInstance(position+1); 
////			break;
////		case 1:
////			getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
////			fragment = BrochuresFragmentContainer.newInstance(position+1); 
////			break;
////		case 2:
////			fragment = CategoryFragmentContainer.newInstance(position+1); 
////			break;
////		case 3:
////			fragment = HomeFragment.newInstance(position+1); 
////			break;
//		default:
//			break;
//
//		}
//
//		if (fragment != null) {
//			FragmentManager fragmentManager = getSupportFragmentManager();
//			fragmentManager.beginTransaction()
//					.replace(R.id.container, fragment).commit();
//
//			// update selected item and title, then close the drawer
//			mDrawerListView.setItemChecked(position, true);
//			mDrawerListView.setSelection(position);
//			setTitle(mDrawerItems[position]);
//			mDrawerLayout.closeDrawer(mDrawerListView);
//			if(position==1){
//				Log.d(TAG, " Brochures : "+mDrawerLayout.isDrawerOpen(mDrawerListView));
//			}
//		} else {
//			// error in creating fragment
//			Log.e("MainActivity", "Error in creating fragment");
//		}
//	}
	
	@Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

	private class DrawerItemClickListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			//navigateTo(position);
			Toast.makeText(parent.getContext(), "Nav Item Selected ", Toast.LENGTH_LONG).show();
		}
		
	}

	public void onSectionAttached(int section) {
		
		setTitle(mDrawerItems[section-1]);
	}
}
