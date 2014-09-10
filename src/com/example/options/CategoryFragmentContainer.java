package com.example.options;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CategoryFragmentContainer extends Fragment {
	
//	public static Fragment newInstance(int sectionNumber) {
//		Fragment details = new CategoryFragmentContainer();
//		Bundle args = new Bundle();
//		args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
//		details.setArguments(args);
//		return details;
//	}

//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//		((OptionsMainActivity) activity).onSectionAttached(getArguments()
//				.getInt(Constants.ARG_SECTION_NUMBER));
//	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
