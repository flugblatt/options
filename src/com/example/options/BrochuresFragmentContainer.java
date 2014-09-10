package com.example.options;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.options.adapter.TabsAdapter;

public class BrochuresFragmentContainer extends Fragment{

	private ViewPager mPager;
	private TabsAdapter mTabsAdapter;
	
	public static Fragment newInstance(int sectionNumber) {
		Fragment details = new BrochuresFragmentContainer();
		Bundle args = new Bundle();
		args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
		details.setArguments(args);
		return details;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//View rootView = getActivity().findViewById(R.id.pager);
		View rootView = inflater.inflate(R.layout.fragment_brochures, container, false);

		// now you can do all your other stuffs
		mPager = new ViewPager(getActivity());
		mPager.setId(R.id.pager);
		getActivity().setContentView(mPager);

		ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar() ;
		mTabsAdapter = new TabsAdapter((ActionBarActivity) getActivity(), mPager);
		mTabsAdapter.addTab(actionBar.newTab().setText("Kaufland"), KauflandFragment.class, null);
		mTabsAdapter.addTab(actionBar.newTab().setText("Rewe"), ReweFragment.class, null);
		mTabsAdapter.addTab(actionBar.newTab().setText("Aldi"), AldiFragment.class, null);

		return rootView;
	}
	
	//	@Override
	//	public void onResume() {
	//		super.onResume();
	//		applyScrollListener();
	//	}
	//
	//	private void applyScrollListener() {
	//		listView.setOnScrollListener(new PauseOnScrollListener(imageLoader, pauseOnScroll, pauseOnFling));
	//	}

}
