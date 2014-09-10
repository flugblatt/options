package com.example.options;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.options.brochures.BrochureListActivity;
import com.example.options.navdrawer.AbstractNavDrawerActivity;
import com.example.options.navdrawer.NavDrawerActivityConfiguration;
import com.example.options.navdrawer.NavDrawerAdapter;
import com.example.options.navdrawer.NavDrawerItem;
import com.example.options.navdrawer.NavMenuBuilder;
import com.example.options.offers.OfferListActivity;

public class OptionsHomeActivity extends AbstractNavDrawerActivity {

	private String TAG = OptionsHomeActivity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			goHomeFragment(this);
		}
	}

	private void goHomeFragment(AbstractNavDrawerActivity activity) {
		activity.getSupportFragmentManager().beginTransaction()
		.replace(R.id.container, new HomeFragment(), HomeFragment.class.getSimpleName()).commit();
		activity.setTitleWithDrawerTitle();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onNavItemSelected(int position) {

		switch (position) {
		case 101:
			Intent offerIntent = new Intent(this, OfferListActivity.class);
			startActivity(offerIntent);
			break;
		case 201:
			Intent categoryIntent = new Intent(this, CategoryListActivity.class);
			startActivity(categoryIntent);
			break;
		case 301:
			Intent brochureIntent = new Intent(this, BrochureListActivity.class);
			startActivity(brochureIntent);
			break;
		default:
			break;

		}
	}

	public void launchOfferActivity(View view) {
		Log.d(TAG, "View : "+view.getTag());
		Intent offerIntent = new Intent(this, OfferListActivity.class);
		//offerIntent.putExtra(OfferDetailFragment.ARG_ITEM_ID, (String) view.getTag());
		startActivity(offerIntent);
	}
	
	@Override
	protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {

		NavMenuBuilder navBuilder = new NavMenuBuilder()
		.addSectionItem(101, R.string.textView_offers, R.drawable.ic_drawer, true, true)
		.addSectionItem(201, R.string.textView_categories, R.drawable.ic_drawer, true, true)
		.addSectionItem(301, R.string.title_brochures, R.drawable.ic_drawer, true, true);

		NavDrawerItem[] menu = navBuilder.build();

		NavDrawerAdapter adapter = new NavDrawerAdapter(this, R.layout.navdrawer_item);
		adapter.setData(menu);

		NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration.Builder()
		.mainLayout(R.layout.activity_drawer)
		.drawerLayoutId(R.id.drawer_layout)
		.leftDrawerId(R.id.list_slidermenu)
		.adapter(adapter)
		.drawerIcon(R.drawable.ic_drawer)
		.build();

		return navDrawerActivityConfiguration;
	}
}
