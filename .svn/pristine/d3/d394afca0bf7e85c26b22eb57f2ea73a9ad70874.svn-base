package com.example.travelmaker.plan;

import com.example.travelmaker.tour.gpsinfomain.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class RegisterPlanActivity extends Activity {
	
	int titleID;
	int day;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cal_layout_regist);
		
		/*PlanListActivity.class로 부터 데이터(planlist TABEL의 _travle과 day) 받음*/
		Intent intent = getIntent();
		titleID = intent.getExtras().getInt("_travel");
		day = intent.getExtras().getInt("day");
		
	}
}
