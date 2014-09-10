package com.example.options;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.options.offers.OfferDetailActivity;
import com.example.options.offers.OfferDetailFragment;

public class HomeFragment extends Fragment {

	private String TAG = HomeFragment.class.getSimpleName();

	GridView grid;
	int[] imageId = {
			R.drawable.kaufland,
			R.drawable.aldi,
			R.drawable.lidl,
			R.drawable.rewe,
			R.drawable.kaufland,
			R.drawable.aldi,
			R.drawable.lidl,
			R.drawable.rewe,
			R.drawable.kaufland,
			R.drawable.aldi,
			R.drawable.lidl,
			R.drawable.rewe,
			R.drawable.kaufland,
			R.drawable.aldi,
			R.drawable.lidl,
			R.drawable.rewe
	};
	String[] mGridArray ; 

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		View rootView = inflater.inflate(R.layout.activity_options_home, container, false);
		
		mGridArray = getActivity().getResources().getStringArray(R.array.grid_offers_array);
		TableLayout tblLayout = (TableLayout) rootView.findViewById(R.id.table1);
		tblLayout = buildTableLayout(tblLayout, imageId, mGridArray, "Offers");
		
		return rootView;
	}

	private TableLayout buildTableLayout(TableLayout tblLayout, int[] imageId2,
			final String[] mGridArray2, final String gridRowName) {
		
		HorizontalScrollView HSV = new HorizontalScrollView(this.getActivity());
		HSV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 
				LayoutParams.FILL_PARENT));

		TableRow tblRow = new TableRow(this.getActivity());
		tblRow.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 
				LayoutParams.WRAP_CONTENT));
		tblRow.setBackgroundResource(R.drawable.ab_texture_tile_example);

		int i=1;
		for(int j = 0; j < imageId2.length; j++) {
			ImageView imageView = new ImageView(this.getActivity());
			imageView.setTag(mGridArray2[i-1]);
			imageView.setImageResource(imageId2[j]);
			imageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Log.d(TAG, "View Tag: "+v.getTag());
					
//					Toast.makeText(getActivity(), "View: "+v.getTag(), Toast.LENGTH_SHORT).show();
					
					Intent offerIntent = new Intent(getActivity(), OfferDetailActivity.class);
					offerIntent.putExtra(OfferDetailFragment.ARG_ITEM_ID, (String) v.getTag());
					startActivity(offerIntent);
					
				}
			});
			i++;
			if((i-1)%4 == 0) i=1;
			tblRow.addView(imageView,j);
		}

		HSV.addView(tblRow);
		tblLayout.addView(HSV);
		return tblLayout;
	}

}
