package com.example.options.offers;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.options.R;
import com.example.options.fragment.FragmentHelper;

public class _OfferListFragment extends ListFragment  {

	private boolean dualPanel;

	/**
	 * @return
	 */
	public static _OfferListFragment newInstance() {
		_OfferListFragment frag = new _OfferListFragment();
		return frag;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see android.support.v4.app.ListFragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mainView = inflater.inflate(R.layout.offers_list_frag, container, false);

		String[] mArray = getActivity().getResources().getStringArray(R.array.grid_offers_array);
		Log.d(_OfferListFragment.class.getSimpleName(), "mArray : "+mArray[0]);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				inflater.getContext(), android.R.layout.simple_list_item_1,
				mArray);

		setListAdapter(adapter);

		return mainView;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.dualPanel = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
	}



	@Override
	public void onResume() {
		super.onResume();
		if ( this.getListView().getSelectedView() != null ) {
			ensureVisible(this.getListView(), 
					this.getListView().getSelectedView());
		}
	}

	private void ensureVisible(ListView parent, View view) {
		parent.smoothScrollToPosition(parent.getSelectedItemPosition());
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see android.support.v4.app.ListFragment#onListItemClick(android.widget.ListView,
	 *      android.view.View, int, long)
	 */
	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {

		super.onListItemClick(listView, view, position, id);

		_OfferDetailFragment detailFragment = _OfferDetailFragment
				.newInstance();
		if (!this.dualPanel) {
			Log.d(_OfferListFragment.class.getSimpleName(), "List Args : "+this.getArguments());
			detailFragment.setArguments(this.getArguments());
			FragmentHelper.initFragmentWithBackstack(detailFragment,
					R.id.offermain_fragment, this.getParentFragment().getChildFragmentManager());
		} else {
			FragmentHelper.initFragment(detailFragment,
					R.id.offerdetail_fragment, this.getParentFragment().getChildFragmentManager());
		}
	}
}
