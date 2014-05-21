/**
 * @author Kim Woo Hyeon
 * InsertActivity.java
 */

package com.example.travelmaker.timetable;

import com.example.travelmaker.tour.gpsinfomain.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class InsertActivity extends Activity implements OnClickListener, OnSeekBarChangeListener {
	
	private static String mInsertedContent;
	private SeekBar mRedSeekBar, mGreenSeekBar, mBlueSeekBar, mAlphaSeekBar;
	private static int mRedValue, mGreenValue, mBlueValue, mAlphaValue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert);
		
		// Initialize;
		Init();
		// Enroll listener;
		SetListener();
	}
	
	private void onClose( int mode ) {
		// TODO Auto-generated method stub
		if( mode == define.INTENT_RESULT_SUCCESS )	setResult(define.INTENT_RESULT_SUCCESS);
		else										setResult(define.INTENT_RESULT_FAIL);
		finish();
	}
	
	@SuppressWarnings("deprecation")
	private void Init() {
		// TODO Auto-generated method stub
		mInsertedContent = "";
		
		((RelativeLayout) findViewById(R.id.insertBackground)).setBackgroundDrawable( MainActivity.GetBackgroundImage() );
		((TextView) findViewById(R.id.insertTitleText)).setText(getIntent().getExtras().getString( define.INTENT_KEY_INSERTTITLE ));
		((TextView) findViewById(R.id.insertContentText)).setText("일정을 등록해주세요");
		((TextView) findViewById(R.id.insertRedColorText)).setText("RED");
		((TextView) findViewById(R.id.insertGreenColorText)).setText("GREEN");
		((TextView) findViewById(R.id.insertBlueColorText)).setText("BLUE");
		((TextView) findViewById(R.id.insertAlphaColorText)).setText("ALPHA");
		
		mRedSeekBar = (SeekBar) findViewById(R.id.insertRedSeekBar);
		mGreenSeekBar = (SeekBar) findViewById(R.id.insertGreenSeekBar);
		mBlueSeekBar = (SeekBar) findViewById(R.id.insertBlueSeekBar);
		mAlphaSeekBar = (SeekBar) findViewById(R.id.insertAlphaSeekBar);
		
		mRedSeekBar.setMax(0xFF);
		mGreenSeekBar.setMax(0xFF);
		mBlueSeekBar.setMax(0xFF);
		mAlphaSeekBar.setMax(0xFF);
		
		mRedSeekBar.setProgress(0);
		mGreenSeekBar.setProgress(0);
		mBlueSeekBar.setProgress(0);
		mAlphaSeekBar.setProgress(0);
		
		mRedValue = mGreenValue = mBlueValue = mAlphaValue = 0;
	}

	private void SetListener() {
		// TODO Auto-generated method stub
		((Button) findViewById(R.id.insertOKBtn)).setOnClickListener(this);
		((Button) findViewById(R.id.insertCancelBtn)).setOnClickListener(this);
		
		mRedSeekBar.setOnSeekBarChangeListener(this);
		mGreenSeekBar.setOnSeekBarChangeListener(this);
		mBlueSeekBar.setOnSeekBarChangeListener(this);
		mAlphaSeekBar.setOnSeekBarChangeListener(this);
	}
	
	public static String GetInsertedContent() {
		// TODO Auto-generated method stub
		return mInsertedContent;
	}
	
	public static int[] GetInsertedARGB() {
		// TODO Auto-generated method stub
		int retVal[] = new int[4];
		retVal[0] = mRedValue;
		retVal[1] = mGreenValue;
		retVal[2] = mBlueValue;
		retVal[3] = mAlphaValue;
		return retVal;
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int position, boolean arg2) {
		// TODO Auto-generated method stub
		switch( seekBar.getId() )
		{
		case R.id.insertRedSeekBar:
			mRedValue = position;
			break;
		case R.id.insertGreenSeekBar:
			mGreenValue = position;
			break;
		case R.id.insertBlueSeekBar:
			mBlueValue = position;
			break;
		case R.id.insertAlphaSeekBar:
			mAlphaValue = position;
			break;
		default:
			break;
		}
		((TextView) findViewById(R.id.insertColorTestText)).setBackgroundColor(Color.argb(mAlphaValue, mRedValue, mGreenValue, mBlueValue));
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch( v.getId() )
		{
		case R.id.insertOKBtn:
			mInsertedContent = ((EditText) findViewById(R.id.insertContentEdit)).getText().toString();
			if( mInsertedContent.equals("") )
				mInsertedContent = " ";
			onClose( define.INTENT_RESULT_SUCCESS );
			break;
		case R.id.insertCancelBtn:
			onClose( define.INTENT_RESULT_FAIL );
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if( keyCode == KeyEvent.KEYCODE_BACK )
		{
			onClose( define.INTENT_RESULT_FAIL );
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
