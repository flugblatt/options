package com.example.options;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class KauflandFragment extends Fragment {
	DisplayImageOptions options;
	String[] imageUrls;
	private final String TAG = "KauflandFragment";
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	protected AbsListView listView;

	public static Fragment newInstance(Bundle args) {
		Fragment details = new KauflandFragment();
		details.setArguments(args);
		return details;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) 
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View simpleFragmentView = inflater.inflate(R.layout.list_kaufland, container, false);

	    imageUrls = Constants.IMAGES_KAUFLAND;

	    options = new DisplayImageOptions.Builder()
	        .showImageOnLoading(R.drawable.ic_launcher)
	        .showImageForEmptyUri(R.drawable.ic_launcher)
	        .showImageOnFail(R.drawable.ic_launcher)
	        .cacheInMemory(true)
	        .cacheOnDisc(true)
	        .considerExifParams(true)
	        .displayer(new RoundedBitmapDisplayer(20))
	        .build();


	    listView = (ListView) simpleFragmentView.findViewById(android.R.id.list);
	    ((ListView) listView).setAdapter(new ItemAdapter(getActivity()));
	    listView.setOnItemClickListener(new OnItemClickListener() 
	    {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
	        {
	            Toast.makeText(getActivity(), "You Clicked at " + position, Toast.LENGTH_SHORT).show();
	            //startImagePagerActivity(position);
	        }
	    });


	    initImageLoader(getActivity());

	    return simpleFragmentView;

	}

	public static void initImageLoader(Context context) 
	{
	    // This configuration tuning is custom. You can tune every option, you may tune some of them,
	    // or you can create default configuration by
	    //  ImageLoaderConfiguration.createDefault(this);
	    // method.

	    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
	            .threadPriority(Thread.NORM_PRIORITY - 2)
	            .denyCacheImageMultipleSizesInMemory()
	            .discCacheFileNameGenerator(new Md5FileNameGenerator())
	            .tasksProcessingOrder(QueueProcessingType.LIFO)
	            .writeDebugLogs() // Remove for release app
	            .build();
	    // Initialize ImageLoader with configuration.
	    ImageLoader.getInstance().init(config);
	}
	
	private static class ViewHolder {
		ImageView image;
	}

	class ItemAdapter extends BaseAdapter {

		private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

		Context context;
	    ItemAdapter(Context context)
	    {
	        Log.d("dexter", "in ItemAdapter Constructor");
	        this.context = context;
	    }
	    
		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = convertView;
			final ViewHolder holder;
			if (convertView == null) {
				view = LayoutInflater.from(context).inflate(R.layout.frag_kaufland, parent, false);
				holder = new ViewHolder();
				holder.image = (ImageView) view.findViewById(R.id.imageView1);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			imageLoader.displayImage(imageUrls[position], holder.image, options, animateFirstListener);

			return view;
		}
	}

	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

}
