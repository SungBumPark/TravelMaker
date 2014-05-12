package com.example.travelmaker.plan;

import java.util.ArrayList;

//import andexam.ver4_1.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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

/**
 * 주변 관광 정보 출력 리스트 엑티비티
 */
public class PlanListActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan_list);
		
		//* 데이터 원본 준비
				ArrayList<String> arGeneral = new ArrayList<String>();
				arGeneral.add("첫째날");
				arGeneral.add("둘째날");
				arGeneral.add("셋째날");
				arGeneral.add("넷째날");
				//*/
				
				/* 배열로 준비
				String[] arGeneral = {"김유신", "이순신", "강감찬", "을지문덕"};
				//*/

				// 어댑터 준비
				ArrayAdapter<String> Adapter;
				Adapter = new ArrayAdapter<String>(this, 
						android.R.layout.simple_list_item_1, arGeneral);

				// 어댑터 연결
				ListView list = (ListView)findViewById(R.id.list);
				list.setAdapter(Adapter);
			}
		// 커스텀 액션바 이용 타이틀 설정

		// x,y 좌표, 반경, 관광 정보 타입 설정
		//Intent intent = getIntent();

	
	}
