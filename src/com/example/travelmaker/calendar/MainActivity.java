package com.example.travelmaker.calendar;

import java.util.Calendar;

import com.example.travelmaker.tour.gpsinfomain.R;

import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends ListActivity implements OnClickListener{

	DbHandler dbHandler;
	EditText edtName; 
	Button btnDpday,btnDptime,btnChday,btnChtime;		//edt 여행제목 출발일,출발시각,귀환일,귀환시간
	Cursor cursor = null;
	////
	DatePickerDialog dateDialog;
	protected int mYear;
	protected int mMonth;
	protected int mDay;
	protected int mHour;
	protected int mMinute;

	int btn_date=0;
	int btn_time=0;
	boolean time = false;
	Calendar c = Calendar.getInstance();
	DatePickerDialog.OnDateSetListener mDateSetListener = 
			new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
			//Log.v("test","NO_func_call");
		}
	};
	TimePickerDialog.OnTimeSetListener mTimeSetListener = 
			new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			updateDisplay();
		}
	};
	////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cal_layout_regist);

		Button btnIns = (Button) findViewById(R.id.btnIns);
		Button btnResult = (Button) findViewById(R.id.btnResult);
		edtName = (EditText) findViewById(R.id.edtName);
		btnDpday = (Button) findViewById(R.id.btnDpday);
		btnDptime = (Button) findViewById(R.id.btnDptime);
		btnChday = (Button) findViewById(R.id.btnChday);
		btnChtime = (Button) findViewById(R.id.btnChtime);

		btnIns.setOnClickListener(this);
		btnResult.setOnClickListener(this);
		btnDpday.setOnClickListener(this);
		btnDptime.setOnClickListener(this);
		btnChday.setOnClickListener(this);
		btnChtime.setOnClickListener(this);

		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);
	}
	@Override
	public void onClick(View v) {
		dbHandler = DbHandler.open(this);

		try{

			///
			if(v.getId()==R.id.btnDpday){
				btn_date = 1;
				new DatePickerDialog(this, 
						mDateSetListener, 
						c.get(Calendar.YEAR),
						c.get(Calendar.MONTH),
						c.get(Calendar.DAY_OF_MONTH)).show();
			}
			else if(v.getId()==R.id.btnDptime){
				time = true;
				btn_time = 1;
				new TimePickerDialog(this,
						mTimeSetListener,
						c.get(Calendar.HOUR_OF_DAY),
						c.get(Calendar.MINUTE),
						true).show();


			}
			else if(v.getId()==R.id.btnChday){
				btn_date = -1;
				new DatePickerDialog(this, 
						mDateSetListener, 
						c.get(Calendar.YEAR),
						c.get(Calendar.MONTH),
						c.get(Calendar.DAY_OF_MONTH)).show();
			}
			else if(v.getId()==R.id.btnChtime){
				time = true;
				btn_time = -1;
				new TimePickerDialog(this,
						mTimeSetListener,
						c.get(Calendar.HOUR_OF_DAY),
						c.get(Calendar.MINUTE),
						true).show();
			}
			///
			if(v.getId()==R.id.btnIns)//추가
				dbHandler.insert(edtName.getText().toString(), 
						btnDpday.getText().toString(),
						btnDptime.getText().toString(),
						btnChday.getText().toString(),
						btnChtime.getText().toString());
			
			else if(v.getId()==R.id.btnResult){//모든자료출력
				cursor = dbHandler.selectAll();

				String[] personArr = new String[cursor.getCount()];//자료개수만큼 배열 만들기 
				int count = 0;

				while(cursor.moveToNext()){
					int id = cursor.getInt(0);
					String code = cursor.getString(1);
					String dpday = cursor.getString(2);
					String dptime = cursor.getString(3);
					String chday = cursor.getString(4);
					String chtime = cursor.getString(5);

					personArr[count] = id + " " + code + " " + dpday + " " + dptime+ " " + chday + " " +chtime;
					count++;
				}//while

				cursor.close();

				ListAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, personArr);
				setListAdapter(adapter);
			}
		}catch (Exception e){
			Toast.makeText(this, "에러발생", 3000).show();
		}

	}//Onclick

	////
	void updateDisplay(){

		int year = mYear;
		int month = mMonth;
		int day = mDay;
		int hour = mHour;
		int minute = mMinute;

		if(time){
			StringBuilder Time = new StringBuilder().append(Timepad(hour)).append(" : ")
					.append(pad(minute));

			if(btn_time > 0){
				btnDptime.setText(Time);
				btn_time = 0;
			}
			else if(btn_time < 0){
				btnChtime.setText(Time);
				btn_time = 0;
			}
			time = false;
		}

		StringBuilder Date = new StringBuilder().append(year).append("년 ")
				.append(pad(month+1)).append("월 ").append(pad(day)).append("일");

		if(btn_date > 0){
			btnDpday.setText(Date);
			btn_date = 0;
		}
		else if(btn_date < 0){
			btnChday.setText(Date);
			btn_date = 0;
		}
	}
	/* am|pm 출력함수 */
	private Object Timepad(int i) {
		if(i>=10){

			if(i>=13)
				return "오후 " + String.valueOf(i-12);
			else if(i==12)
				return "오후" + String.valueOf(i);
			else
				return "오전 " + String.valueOf(i);
		}
		else
			return "오전 0" + String.valueOf(i);
	}
	/* mm월 dd일 형식으로 출력하는 함수 */

	protected Object pad(int i) {
		if(i>=10)
			return String.valueOf(i);
		else
			return "0" + String.valueOf(i);
	}

	////

}