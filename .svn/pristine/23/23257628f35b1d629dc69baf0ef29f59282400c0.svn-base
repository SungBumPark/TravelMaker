/**
 * @author Kim Woo Hyeon
 * MakeActivity.java
 */

package com.example.travelmaker.timetable;

import com.example.travelmaker.tour.gpsinfomain.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TimePicker;

public class MakeActivity extends Activity implements OnClickListener {
	
	private NumberPicker mTimeCountNumberPicker, mTimeSpaceNumberPicker, mBreakTimeNumberPicker;
	private TimePicker mStartTimePicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make);

		// Initialize;
		Init();
		// Enroll listener;
		SetListener();
	}

	@SuppressWarnings("deprecation")
	private void Init() {
		// TODO Auto-generated method stub
		((ScrollView) findViewById(R.id.makeBackground)).setBackgroundDrawable( MainActivity.GetBackgroundImage() );
		
		mTimeCountNumberPicker = (NumberPicker) findViewById(R.id.timeCountNumberPicker);
		mTimeCountNumberPicker.setMinValue(1);
		mTimeCountNumberPicker.setMaxValue(20);
		mTimeCountNumberPicker.setValue(5);
		mStartTimePicker = (TimePicker) findViewById(R.id.startTimePicker);
		mStartTimePicker.setIs24HourView( true );
		mStartTimePicker.setCurrentHour(9);
		mStartTimePicker.setCurrentMinute(0);
		mTimeSpaceNumberPicker = (NumberPicker) findViewById(R.id.timeSpaceNumberPicker);
		mTimeSpaceNumberPicker.setMinValue(1);
		mTimeSpaceNumberPicker.setMaxValue(80);
		mTimeSpaceNumberPicker.setValue(50);
		mBreakTimeNumberPicker = (NumberPicker) findViewById(R.id.breakTimeNumberPicker);
		mBreakTimeNumberPicker.setMinValue(0);
		mBreakTimeNumberPicker.setMaxValue(40);
		mBreakTimeNumberPicker.setValue(10);
	}

	private void SetListener() {
		// TODO Auto-generated method stub
		((Button) findViewById(R.id.makeTableBtn)).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if( v.getId() == R.id.makeTableBtn )
		{
			String msg =	"교시 선택 : " + mTimeCountNumberPicker.getValue() + "교시\n" +
							"시작 시간 : " + mStartTimePicker.getCurrentHour() + "시 " + mStartTimePicker.getCurrentMinute() + "분\n" +
							"시간 간격 : " + mTimeSpaceNumberPicker.getValue() + "분\n" +
							"쉬는 시간 : " + mBreakTimeNumberPicker.getValue() + "분\n" +
							"\n현재 값으로 시간표를 만들까요?";
			new AlertDialog.Builder(this)
			.setIcon(R.drawable.ic_launcher)
			.setTitle("만들기")
		    .setMessage(msg)
			.setNegativeButton(com.example.travelmaker.tour.gpsinfomain.R.string.common_use_ok_btn, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
					Intent intent = new Intent( MakeActivity.this, TableActivity.class );
					intent.putExtra( define.INTENT_KEY_TIMECOUNT, mTimeCountNumberPicker.getValue() );
					intent.putExtra( define.INTENT_KEY_STARTTIMEHOUR, mStartTimePicker.getCurrentHour() );
					intent.putExtra( define.INTENT_KEY_STARTTIMEMIN, mStartTimePicker.getCurrentMinute() );
					intent.putExtra( define.INTENT_KEY_TIMESPACE, mTimeSpaceNumberPicker.getValue() );
					intent.putExtra( define.INTENT_KEY_BREAKTIME, mBreakTimeNumberPicker.getValue() );
					startActivity(intent);
				}
			})
			.setPositiveButton(com.example.travelmaker.tour.gpsinfomain.R.string.common_use_cancel_btn, null)
			.show();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if( keyCode == KeyEvent.KEYCODE_BACK )
			finish();
		return super.onKeyDown(keyCode, event);
	}
	
}
