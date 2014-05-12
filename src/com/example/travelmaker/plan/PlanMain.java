package com.example.travelmaker.plan;

import java.util.ArrayList;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.ImageButton;
//import android.widget.ListView;
//import android.widget.TextView;
import com.example.travelmaker.tour.gpsinfomain.*;

public class PlanMain extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage_travel);
		
		Button btn1;
		btn1=(Button) findViewById(R.id.btn1);
		
		btn1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
				Intent intent=new Intent(PlanMain.this, PlanListActivity.class);
				startActivity(intent);
				}
				
		 });
	
	}
	}