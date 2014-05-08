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
public class Tab2 extends Fragment {
		Context mContext;
		TextView tv;
		
		public Tab2(Context context) {
			mContext = context;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, 
				ViewGroup container, Bundle savedInstanceState) {
			setHasOptionsMenu(true);
			View view = inflater.inflate(R.layout.tab, null);
			
			tv = (TextView) view.findViewById(R.id.tv2);
			tv.setText("���� ���� ������ �Դϴ�.");
						
	    	return view;
		}
		
		
		 @Override
		 public void onPrepareOptionsMenu(Menu menu) {
		        menu.findItem(R.id.menu_item1).setTitle("���� ���");
		        menu.findItem(R.id.menu_item2).setTitle("���� ����");
		        menu.findItem(R.id.menu_item3).setTitle("��ũ��");
		    }
		 
		 @Override
		 public boolean onOptionsItemSelected(MenuItem item) {
		     switch(item.getItemId()){
		     case R.id.menu_item1:
		    	  Toast.makeText(mContext, "���� ���", Toast.LENGTH_SHORT)
	              .show();
		    	  break;
		     case R.id.menu_item2:
		    	  Toast.makeText(mContext, "���� ����", Toast.LENGTH_SHORT)
	              .show();
		    	  break;
		     case R.id.menu_item3:
		    	  Toast.makeText(mContext, "��ũ��", Toast.LENGTH_SHORT)
	              .show();
		    	  break;
    
		     default:
		         return super.onOptionsItemSelected(item);
		     }
			return true;
		 }
	}