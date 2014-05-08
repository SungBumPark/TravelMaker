package com.example.travelmaker.tab;

import com.example.travelmaker.tour.gpsinfomain.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Jin ah Lee
 * @since 2013.08.03
 */

@SuppressLint("ValidFragment")
public class Tab3 extends Fragment {
		Context mContext;
		TextView tv;
		
		public Tab3(Context context) {
			mContext = context;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, 
				ViewGroup container, Bundle savedInstanceState) {
			setHasOptionsMenu(true);
			View view = inflater.inflate(R.layout.tab, null);

			tv = (TextView) view.findViewById(R.id.tv2);
			tv.setText("커뮤니티 페이지 입니다!");
			
	    	return view;
		}
		
		 @Override
		 public void onPrepareOptionsMenu(Menu menu) {
		        menu.findItem(R.id.menu_item1).setTitle("게시판");
		        menu.findItem(R.id.menu_item2).setTitle("여행담");
		        menu.findItem(R.id.menu_item3).setTitle("SNS");
		    }
		 
		 @Override
		 public boolean onOptionsItemSelected(MenuItem item) {
		     switch(item.getItemId()){
		     case R.id.menu_item1:
		    	  Toast.makeText(mContext, "게시판", Toast.LENGTH_SHORT)
	              .show();
		    	  break;
		     case R.id.menu_item2:
		    	  Toast.makeText(mContext, "여행담", Toast.LENGTH_SHORT)
	              .show();
		    	  break;
		     case R.id.menu_item3:
		    	  Toast.makeText(mContext, "SNS", Toast.LENGTH_SHORT)
	              .show();
		    	  break;
    
		     default:
		         return super.onOptionsItemSelected(item);
		     }
			return true;
		 }

}