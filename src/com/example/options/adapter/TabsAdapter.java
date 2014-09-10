package com.example.options.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class TabsAdapter extends FragmentPagerAdapter implements TabListener,
OnPageChangeListener {

	private final Context mContext;
	private final ViewPager mPager;
	private final ActionBar mActionBar;
	private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
	private final String TAG = "TabsAdapter";

	static final class TabInfo {
		private final Class<?> clss;
		private final Bundle bundle;

		public TabInfo(Class<?> _class, Bundle _bundle) {
			clss = _class;
			bundle = _bundle;
		}
	}

	public TabsAdapter(ActionBarActivity fragActivity, ViewPager vPager) {
		super(fragActivity.getSupportFragmentManager());
		mContext = fragActivity;
		mPager = vPager;
		mActionBar = fragActivity.getSupportActionBar();
		mPager.setAdapter(this);
		mPager.setOnPageChangeListener(this);
	}
	
	public void addTab(Tab tab, Class<?> clss, Bundle bundle){
		TabInfo info = new TabInfo(clss, bundle);
		tab.setTag(info);
		tab.setTabListener(this);
		mTabs.add(info);
		mActionBar.addTab(tab);
		Log.d(TAG, "Tab Added : "+tab.getText());
		notifyDataSetChanged();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position) {
		mActionBar.setSelectedNavigationItem(position);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mPager.setCurrentItem(tab.getPosition());
		Log.d(TAG, "Tab Selected : "+tab.getPosition());
		Object tag = tab.getTag();
		for (int i = 0; i < mTabs.size(); i++) {
			if (mTabs.get(i)==tag) {
				mPager.setCurrentItem(i);
			}
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public Fragment getItem(int position) {
		TabInfo info = mTabs.get(position);
		return ListFragment.instantiate(mContext, info.clss.getName(), info.bundle);
	}

	@Override
	public int getCount() {
		return mTabs.size();
	}

}
