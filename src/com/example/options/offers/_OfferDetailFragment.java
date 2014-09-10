package com.example.options.offers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.options.Constants;
import com.example.options.R;

public class _OfferDetailFragment extends Fragment {

    /**
     * @return
     */
    public static _OfferDetailFragment newInstance() {
        _OfferDetailFragment frag = new _OfferDetailFragment();
        return frag;
    }

    /**
	 * @param detailUri
	 * @return
	 */
	public static _OfferDetailFragment newInstance(String storeName) {
		_OfferDetailFragment frag = new _OfferDetailFragment();
		Bundle args = new Bundle();
		frag.setArguments(args);
		return frag;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View mainView = inflater.inflate(R.layout.offer_detail, container,
				false);

		return mainView;
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Log.d(_OfferDetailFragment.class.getSimpleName(), "Args : "+this.getArguments());
		TextView text = (TextView) getActivity().findViewById(R.id.offer_frag_detail);
		text.setText(this.getArguments().getString(Constants.ARG_OFFER_GRID));
	}

}
