package com.example.options.offers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.options.R;
import com.example.options.fragment.MasterDetailFragmentHelper;
import com.example.options.fragment.MasterDetailFragments;

public class OfferMainFragment extends Fragment {

	public static Fragment newInstance() {
		Fragment details = new OfferMainFragment();
		return details;
	}
	
//	@Override
//	public View onCreateView(LayoutInflater inflater,
//			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//		super.onCreateView(inflater, container, savedInstanceState);
//		View offersView = inflater.inflate(R.layout.offers_list_frag, container, false);
//		
//		TextView text = (TextView) offersView.findViewById(R.id.txt_offers_list_frag);
//		text.setText(this.getArguments().getString(Constants.ARG_OFFER_GRID));
//		return offersView;
//	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.d(OfferMainFragment.class.getSimpleName(), "offer main : "+getArguments());
        setRetainInstance(false);
        setHasOptionsMenu(false);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
        View view = inflater.inflate(R.layout.offers_main_frag, container, false);

        MasterDetailFragments currentFragments = MasterDetailFragmentHelper
                .getCurrentFragments(R.id.offermain_fragment,
                        R.id.offerdetail_fragment, _OfferDetailFragment.class,
                        getChildFragmentManager());
        if ( currentFragments.master == null ) {
            currentFragments.master = _OfferListFragment.newInstance();
        }
        
        MasterDetailFragmentHelper.initFragments(currentFragments, R.id.offermain_fragment,
                R.id.offerdetail_fragment, getResources().getConfiguration(), getChildFragmentManager());

		return view ;
	}

}
