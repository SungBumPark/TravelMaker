/**
 * @author Kim Woo Hyeon
 * TableActivity.java
 */

package com.example.travelmaker.timetable;

import com.example.travelmaker.tour.gpsinfomain.R;
import com.example.travelmaker.tour.gpsinfomain.R.string;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TableActivity extends Activity implements OnItemClickListener {
	
	private final int COLUMN_COUNT	= 10;
	private int mTimeCount;
	private int mStartTimeHour;
	private int mStartTimeMin;
	private int mTimeSpace;
	private int mBreakTime;
	private String mUpdateTimeSendString, mInsertTitleSendString, mUpdateContentSendString;
	private int mUpdateTimePosition, mInsertedItemPosition, mUpdatedItemPosition;
	
	private GridView mTimeTable;
	private ListAdapter mAdapter;
	private String mItemTextArray[];
	private int mRedColorArray[], mGreenColorArray[], mBlueColorArray[], mAlphaColorArray[];
	
	class ScheduleAdapter extends BaseAdapter {

		Context mContext;
		int mCount = (mTimeCount + 1) * COLUMN_COUNT;
		String mStrText[];
		int mDisplayWidth, mDisplayHeight;
		TextPaint mPaint;
		
		ScheduleAdapter(Context context, String str[]){
			mStrText = str;
			mContext = context;
			
			DisplayMetrics displayMetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			mDisplayWidth = displayMetrics.widthPixels;
			mDisplayHeight = displayMetrics.heightPixels;
			
			mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
			mPaint.density = displayMetrics.density;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mCount;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View oldView, ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView items = new TextView(mContext);
			items.setHeight(mDisplayHeight/16);				// 각 아이템 높이
			items.setGravity(Gravity.CENTER);
			if( mStrText[position] == null )
				items.setText(mStrText[position]);
			else
			{
				String ellipsizeStr = TextUtils.ellipsize(mStrText[position], mPaint, mDisplayWidth / COLUMN_COUNT, TruncateAt.START).toString(); 
				items.setText(ellipsizeStr);
			}
			if( (position < COLUMN_COUNT) )				items.setTextSize(20);
			else if( (position % COLUMN_COUNT == 0) )	items.setTextSize(16);
			else										items.setTextSize(15);
			//items.setBackgroundColor(Color.GRAY);
			items.setBackgroundColor(Color.argb(mAlphaColorArray[position], mRedColorArray[position], mGreenColorArray[position], mBlueColorArray[position]));

			return items;
		}

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table);
		
		// Received Intent Value;
		GetIntentValue();
		// Initialize;
		Init();
	}
	
	private void GetIntentValue() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		mTimeCount = intent.getExtras().getInt( define.INTENT_KEY_TIMECOUNT );
		mRedColorArray = new int[(mTimeCount + 1) * COLUMN_COUNT];
		mGreenColorArray = new int[(mTimeCount + 1) * COLUMN_COUNT];
		mBlueColorArray = new int[(mTimeCount + 1) * COLUMN_COUNT];
		mAlphaColorArray = new int[(mTimeCount + 1) * COLUMN_COUNT];
		mItemTextArray =  new String[(mTimeCount + 1) * COLUMN_COUNT];
		
		if( intent.getExtras().getBoolean( define.INTENT_KEY_LOAD_START ) )
		{
			String getTime = intent.getExtras().getString( define.INTENT_KEY_LOAD_TIME );
			String getContent_count = intent.getExtras().getString( define.INTENT_KEY_CONTENT_COUNT );
			String getContent_locate = intent.getExtras().getString( define.INTENT_KEY_CONTENT_LOCATE );
			String getContent_red = intent.getExtras().getString( define.INTENT_KEY_CONTENT_RED );
			String getContent_green = intent.getExtras().getString( define.INTENT_KEY_CONTENT_GREEN );
			String getContent_blue = intent.getExtras().getString( define.INTENT_KEY_CONTENT_BLUE );
			String getContent_alpha = intent.getExtras().getString( define.INTENT_KEY_CONTENT_ALPHA );
			String getContent = intent.getExtras().getString( define.INTENT_KEY_CONTENT );
			
			int content_count = Integer.parseInt( getContent_count );
			// Get time array & token;
			int time_count_temp = 0;
			String time[] = new String[mTimeCount];
			StringTokenizer token_time = new StringTokenizer(getTime, ",");
			for( int idx = 0; idx < mTimeCount; ++idx )
				time[idx] = token_time.nextToken();
			// Get content_locate array & token;
			String content_locate[] = new String[content_count];
			StringTokenizer token_content_locate = new StringTokenizer(getContent_locate, ",");
			for( int idx = 0; idx < content_count; ++idx )
				content_locate[idx] = token_content_locate.nextToken();
			// Get content_red array & token;
			int content_red_count_temp = 0;
			String content_red[] = new String[content_count];
			StringTokenizer token_content_red = new StringTokenizer(getContent_red, ",");
			for( int idx = 0; idx < content_count; ++idx )
				content_red[idx] = token_content_red.nextToken();
			// Get content_green array & token;
			int content_green_count_temp = 0;
			String content_green[] = new String[content_count];
			StringTokenizer token_content_green = new StringTokenizer(getContent_green, ",");
			for( int idx = 0; idx < content_count; ++idx )
				content_green[idx] = token_content_green.nextToken();
			// Get content_blue array & token;
			int content_blue_count_temp = 0;
			String content_blue[] = new String[content_count];
			StringTokenizer token_content_blue = new StringTokenizer(getContent_blue, ",");
			for( int idx = 0; idx < content_count; ++idx )
				content_blue[idx] = token_content_blue.nextToken();
			// Get content_alpha array & token;
			int content_alpha_count_temp = 0;
			String content_alpha[] = new String[content_count];
			StringTokenizer token_content_alpha = new StringTokenizer(getContent_alpha, ",");
			for( int idx = 0; idx < content_count; ++idx )
				content_alpha[idx] = token_content_alpha.nextToken();
			// Get content array & token;
			int content_count_temp = 0;
			String content[] = new String[content_count];
			StringTokenizer token_content = new StringTokenizer(getContent, ",");
			for( int idx = 0; idx < content_count; ++idx )
				content[idx] = token_content.nextToken();
			
			for( int idx1 = COLUMN_COUNT; idx1 < (mTimeCount + 1) * COLUMN_COUNT; ++idx1 )
				if( idx1 % COLUMN_COUNT == 0 )
					mItemTextArray[idx1] = time[time_count_temp++];
				else
					for( int idx2 = 0; idx2 < content_count; ++idx2 )
						if( idx1 == Integer.parseInt(content_locate[idx2]) )
						{
							mRedColorArray[idx1] = Integer.parseInt(content_red[content_red_count_temp++]);
							mGreenColorArray[idx1] = Integer.parseInt(content_green[content_green_count_temp++]);
							mBlueColorArray[idx1] = Integer.parseInt(content_blue[content_blue_count_temp++]);
							mAlphaColorArray[idx1] = Integer.parseInt(content_alpha[content_alpha_count_temp++]);
							mItemTextArray[idx1] = content[content_count_temp++];
							break;
						}
		}
		else
		{
			mStartTimeHour = intent.getExtras().getInt( define.INTENT_KEY_STARTTIMEHOUR );
			mStartTimeMin = intent.getExtras().getInt( define.INTENT_KEY_STARTTIMEMIN );
			mTimeSpace = intent.getExtras().getInt( define.INTENT_KEY_TIMESPACE );
			mBreakTime = intent.getExtras().getInt( define.INTENT_KEY_BREAKTIME );
		}
	}
	
	@SuppressWarnings("deprecation")
	private void Init() {
		// TODO Auto-generated method stub
		((RelativeLayout) findViewById(R.id.tableBackground)).setBackgroundDrawable( MainActivity.GetBackgroundImage() );

		// Default Text;
		mItemTextArray[1] = "1일";
		mItemTextArray[2] = "2일";
		mItemTextArray[3] = "3일";
		mItemTextArray[4] = "4일";
		mItemTextArray[5] = "5일";
		mItemTextArray[6] = "6일";
		mItemTextArray[7] = "7일";
		mItemTextArray[8] = "8일";
		mItemTextArray[9] = "9일";
		
		
		for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
		{
			if( idx % COLUMN_COUNT == 0 && mItemTextArray[idx] == null )
			{
				String str = GetTimeString(mStartTimeHour) + ":" + GetTimeString(mStartTimeMin) + "~";
				mItemTextArray[idx] = str;
				mStartTimeMin += mTimeSpace;
				if( mStartTimeMin >= 60 )
				{
					mStartTimeMin -= 60;
					mStartTimeHour += 1;
					if( mStartTimeHour >= 24 )
						mStartTimeHour = 0;
				}
				str = GetTimeString(mStartTimeHour) + ":" + GetTimeString(mStartTimeMin);
				mItemTextArray[idx] += str;
				mStartTimeMin += mBreakTime;
				if( mStartTimeMin >= 60 )
				{
					mStartTimeMin -= 60;
					mStartTimeHour += 1;
					if( mStartTimeHour >= 24 )
						mStartTimeHour = 0;
				}
			}
		}
		
		String strArray[] = new String[mItemTextArray.length];
		for( int idx = 0; idx < mItemTextArray.length; ++idx )
			strArray[idx] = ( (idx >= COLUMN_COUNT) && (idx % COLUMN_COUNT == 0) ) ? GetTimeString(idx / COLUMN_COUNT) + "교시" : mItemTextArray[idx];
		
		mTimeTable =    (GridView) findViewById(R.id.timeTable);
		//mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
		mTimeTable.setNumColumns(COLUMN_COUNT);
		mAdapter = new ScheduleAdapter(this, strArray);
		mTimeTable.setAdapter( mAdapter );
		mTimeTable.setOnItemClickListener(this);
		
	}
	
	private String GetTimeString( int nValue ) {
		// TODO Auto-generated method stub
		String str = "";
		if( nValue < 10 )
			str = "0" + nValue;
		else
			str = "" + nValue;
		return str;
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapter, View v, int position, long ID) {
		// TODO Auto-generated method stub
		if( position < COLUMN_COUNT )
		{
			new AlertDialog.Builder(this)
			.setIcon(R.drawable.ic_launcher)
			.setTitle("요일")
		    .setMessage(mItemTextArray[position] + "요일 입니다.")
			.setPositiveButton(string.common_use_ok_btn, null)
			.show();
		}
		else if( position % COLUMN_COUNT == 0 )
		{
			mUpdateTimeSendString = mItemTextArray[position];
			mUpdateTimePosition = position;
			
			new AlertDialog.Builder(this)
			.setIcon(R.drawable.ic_launcher)
			.setTitle(GetTimeString(position / COLUMN_COUNT) + "교시")
		    .setMessage("선택하신 " + GetTimeString(position / COLUMN_COUNT) + "교시는 " + mItemTextArray[position] + "시간 입니다.\n" + 
		    			"수정 하시겠습니까?")
		    .setNegativeButton(string.common_use_ok_btn, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					StringTokenizer token = new StringTokenizer(mUpdateTimeSendString, ":~");
					int tokenedValue[] = new int[]{0,0,0,0};
					for( int idx = 0; idx < tokenedValue.length; ++idx )
						tokenedValue[idx] = Integer.parseInt(token.nextToken());
					Intent intent = new Intent(TableActivity.this, UpdateTimeActivity.class);
					intent.putExtra(define.INTENT_KEY_STARTTIMEHOUR, tokenedValue[0]);
					intent.putExtra(define.INTENT_KEY_STARTTIMEMIN, tokenedValue[1]);
					intent.putExtra(define.INTENT_KEY_ENDTIMEHOUR, tokenedValue[2]);
					intent.putExtra(define.INTENT_KEY_ENDTIMEMIN, tokenedValue[3]);
					startActivityForResult(intent, define.INTENT_REQUEST_UPDATETIME);
				}
			})
			.setPositiveButton(string.common_use_cancel_btn, null)
			.show();
		}
		else
		{
			if( mItemTextArray[position] == null )
			{
				mInsertTitleSendString = mItemTextArray[position % COLUMN_COUNT] + "요일 " + GetTimeString(position / COLUMN_COUNT) + "교시\n" +
										 "(" + mItemTextArray[COLUMN_COUNT * (position / COLUMN_COUNT)] + ")";
				mInsertedItemPosition = position;
				
				new AlertDialog.Builder(this)
				.setIcon(R.drawable.ic_launcher)
				.setTitle("입력하기")
			    .setMessage(mItemTextArray[position % COLUMN_COUNT] + "요일 " + GetTimeString(position / COLUMN_COUNT) + "교시에 값을 입력하시겠습니까?")
			    .setNegativeButton(string.common_use_ok_btn, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(TableActivity.this, InsertActivity.class);
						intent.putExtra(define.INTENT_KEY_INSERTTITLE, mInsertTitleSendString);
						startActivityForResult(intent, define.INTENT_REQUEST_INSERTCONTENT);
					}
				})
				.setPositiveButton(string.common_use_cancel_btn, null)
				.show();
			}
			else
			{
				mInsertTitleSendString = mItemTextArray[position % COLUMN_COUNT] + "요일 " + GetTimeString(position / COLUMN_COUNT) + "교시\n" +
						 "(" + mItemTextArray[COLUMN_COUNT * (position / COLUMN_COUNT)] + ")";
				mUpdateContentSendString = mItemTextArray[position];
				mUpdatedItemPosition = position;
				
				new AlertDialog.Builder(this)
				.setIcon(R.drawable.ic_launcher)
				.setTitle("수정하기")
			    .setMessage(mItemTextArray[position % COLUMN_COUNT] + "요일 " + GetTimeString(position / COLUMN_COUNT) + "교시에 입력된 값은 " + "'" + mItemTextArray[position] + "'" + "입니다.\n" +
			    			"수정하시겠습니까?")
			    .setNegativeButton(string.common_use_ok_btn, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(TableActivity.this, UpdateActivity.class);
						intent.putExtra(define.INTENT_KEY_UPDATETITLE, mInsertTitleSendString);
						intent.putExtra(define.INTENT_KEY_UPDATECONTENT, mUpdateContentSendString);
						intent.putExtra(define.INTENT_KEY_UPDATE_RED, mRedColorArray[mUpdatedItemPosition]);
						intent.putExtra(define.INTENT_KEY_UPDATE_GREEN, mGreenColorArray[mUpdatedItemPosition]);
						intent.putExtra(define.INTENT_KEY_UPDATE_BLUE, mBlueColorArray[mUpdatedItemPosition]);
						intent.putExtra(define.INTENT_KEY_UPDATE_ALPHA, mAlphaColorArray[mUpdatedItemPosition]);
						startActivityForResult(intent, define.INTENT_REQUEST_UPDATECONTENT);
					}
				})
				.setPositiveButton(string.common_use_cancel_btn, null)
				.show();
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		switch( requestCode )
		{
		case define.INTENT_REQUEST_UPDATETIME:
			if( resultCode == define.INTENT_RESULT_SUCCESS )
			{
				mItemTextArray[mUpdateTimePosition] = UpdateTimeActivity.GetUpdatedTime();
			}
			break;
		case define.INTENT_REQUEST_INSERTCONTENT:
			if( resultCode == define.INTENT_RESULT_SUCCESS )
			{
				mItemTextArray[mInsertedItemPosition] = InsertActivity.GetInsertedContent();
				// Get ARGB Value;
				int colorValue[] = InsertActivity.GetInsertedARGB();
				mRedColorArray[mInsertedItemPosition] = colorValue[0];
				mGreenColorArray[mInsertedItemPosition] = colorValue[1];
				mBlueColorArray[mInsertedItemPosition] = colorValue[2];
				mAlphaColorArray[mInsertedItemPosition] = colorValue[3];
				// Invalidate;
				String strArray[] = new String[mItemTextArray.length];
				for( int idx = 0; idx < mItemTextArray.length; ++idx )
					strArray[idx] = ( (idx >= COLUMN_COUNT) && (idx % COLUMN_COUNT == 0) ) ? GetTimeString(idx / COLUMN_COUNT) + "교시" : mItemTextArray[idx];
				mAdapter = new ScheduleAdapter(this, strArray);
				mTimeTable.setAdapter( mAdapter );
			}
			break;
		case define.INTENT_REQUEST_UPDATECONTENT:
			if( resultCode == define.INTENT_RESULT_SUCCESS )
			{
				mItemTextArray[mUpdatedItemPosition] = UpdateActivity.GetUpdatedContent();
				// Get ARGB Value;
				int colorValue[] = UpdateActivity.GetUpdatedARGB();
				mRedColorArray[mUpdatedItemPosition] = colorValue[0];
				mGreenColorArray[mUpdatedItemPosition] = colorValue[1];
				mBlueColorArray[mUpdatedItemPosition] = colorValue[2];
				mAlphaColorArray[mUpdatedItemPosition] = colorValue[3];
				// Invalidate;
				String strArray[] = new String[mItemTextArray.length];
				for( int idx = 0; idx < mItemTextArray.length; ++idx )
					strArray[idx] = ( (idx >= COLUMN_COUNT) && (idx % COLUMN_COUNT == 0) ) ? GetTimeString(idx / COLUMN_COUNT) + "교시" : mItemTextArray[idx];
				mAdapter = new ScheduleAdapter(this, strArray);
				mTimeTable.setAdapter( mAdapter );
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 0, "저장");
		menu.add(0, 2, 0, "종료");
		return true;
	}
	
//	옵션 메뉴 호출
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if( item.getItemId() == 1 ) {
			Context mContext = getApplicationContext();
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
			final View layout = inflater.inflate(R.layout.dialog_save,(ViewGroup) findViewById(R.id.saveDialog));
			new AlertDialog.Builder(this)
			.setIcon(R.drawable.ic_launcher)
			.setTitle("저장하기")
			.setView(layout)
			.setNegativeButton(string.common_use_ok_btn, new DialogInterface.OnClickListener() {
				
				String save_name = "";
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					save_name = ((EditText) layout.findViewById(R.id.saveDialogEdit)).getText().toString();
					ArrayList<String> items = new ArrayList<String>();
					Cursor cursor = MainActivity.GetDatabase().selectName(MainActivity.GetSQLiteDatabase());
					while( cursor.moveToNext() )
						items.add( cursor.getString(0) );
					boolean bOverlap = false;
					for( int idx = 0; idx < items.size(); ++idx )
						if( save_name.equals(items.get(idx)) ) 
								bOverlap = true;
					if( bOverlap )
					{
						new AlertDialog.Builder(TableActivity.this)
						.setIcon(R.drawable.ic_launcher)
						.setTitle("저장하기")
						.setMessage( "'" + save_name + "'와 중복된 이름이 있습니다.\n" +
									"그래도 저장하시겠습니까?")
						.setNegativeButton(string.common_use_ok_btn, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								MainActivity.GetDatabase().deleteName(MainActivity.GetSQLiteDatabase(), save_name);
								String time_count = "" + mTimeCount;
								String time = "";
								for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
									if( idx == COLUMN_COUNT )
										time = mItemTextArray[idx];
									else if( idx % COLUMN_COUNT == 0 )
										time += "," + mItemTextArray[idx];
								String content_count = "";
								int count = 0;
								for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
									if( idx % COLUMN_COUNT != 0 )
										if( mItemTextArray[idx] != null )
											++count;
								content_count = "" + count;
								String content_locate = "";
								for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
									if( idx % COLUMN_COUNT != 0 )
										if( mItemTextArray[idx] != null )
											if( content_locate.equals("") )
												content_locate = "" + idx;
											else
												content_locate += "," + idx;
								String content_red = "";
								for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
									if( idx % COLUMN_COUNT != 0 )
										if( mItemTextArray[idx] != null )
											if( content_red.equals("") )
												content_red = "" + mRedColorArray[idx];
											else
												content_red += "," + mRedColorArray[idx];
								String content_green = "";
								for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
									if( idx % COLUMN_COUNT != 0 )
										if( mItemTextArray[idx] != null )
											if( content_green.equals("") )
												content_green = "" + mGreenColorArray[idx];
											else
												content_green += "," + mGreenColorArray[idx];
								String content_blue = "";
								for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
									if( idx % COLUMN_COUNT != 0 )
										if( mItemTextArray[idx] != null )
											if( content_blue.equals("") )
												content_blue = "" + mBlueColorArray[idx];
											else
												content_blue += "," + mBlueColorArray[idx];
								String content_alpha = "";
								for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
									if( idx % COLUMN_COUNT != 0 )
										if( mItemTextArray[idx] != null )
											if( content_alpha.equals("") )
												content_alpha = "" + mAlphaColorArray[idx];
											else
												content_alpha += "," + mAlphaColorArray[idx];
								String content = "";
								for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
									if( idx % COLUMN_COUNT != 0 )
										if( mItemTextArray[idx] != null )
											if( content.equals("") )
												content = "" + mItemTextArray[idx];
											else
												content += "," + mItemTextArray[idx];
								MainActivity.GetDatabase().insertValue(MainActivity.GetSQLiteDatabase(), save_name, time_count, time, content_count, content_locate, content_red, content_green, content_blue, content_alpha, content);
								MainActivity.ShowPopup("수정하기", "수정되었습니다.", TableActivity.this);
							}
						})
						.setPositiveButton(string.common_use_cancel_btn, null)
						.show();
					}
					else
					{
						String time_count = "" + mTimeCount;
						String time = "";
						for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
							if( idx == COLUMN_COUNT )
								time = mItemTextArray[idx];
							else if( idx % COLUMN_COUNT == 0 )
								time += "," + mItemTextArray[idx];
						String content_count = "";
						int count = 0;
						for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
							if( idx % COLUMN_COUNT != 0 )
								if( mItemTextArray[idx] != null )
									++count;
						content_count = "" + count;
						String content_locate = "";
						for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
							if( idx % COLUMN_COUNT != 0 )
								if( mItemTextArray[idx] != null )
									if( content_locate.equals("") )
										content_locate = "" + idx;
									else
										content_locate += "," + idx;
						String content_red = "";
						for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
							if( idx % COLUMN_COUNT != 0 )
								if( mItemTextArray[idx] != null )
									if( content_red.equals("") )
										content_red = "" + mRedColorArray[idx];
									else
										content_red += "," + mRedColorArray[idx];
						String content_green = "";
						for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
							if( idx % COLUMN_COUNT != 0 )
								if( mItemTextArray[idx] != null )
									if( content_green.equals("") )
										content_green = "" + mGreenColorArray[idx];
									else
										content_green += "," + mGreenColorArray[idx];
						String content_blue = "";
						for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
							if( idx % COLUMN_COUNT != 0 )
								if( mItemTextArray[idx] != null )
									if( content_blue.equals("") )
										content_blue = "" + mBlueColorArray[idx];
									else
										content_blue += "," + mBlueColorArray[idx];
						String content_alpha = "";
						for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
							if( idx % COLUMN_COUNT != 0 )
								if( mItemTextArray[idx] != null )
									if( content_alpha.equals("") )
										content_alpha = "" + mAlphaColorArray[idx];
									else
										content_alpha += "," + mAlphaColorArray[idx];
						String content = "";
						for( int idx = COLUMN_COUNT; idx < mItemTextArray.length; ++idx )
							if( idx % COLUMN_COUNT != 0 )
								if( mItemTextArray[idx] != null )
									if( content.equals("") )
										content = "" + mItemTextArray[idx];
									else
										content += "," + mItemTextArray[idx];
						MainActivity.GetDatabase().insertValue(MainActivity.GetSQLiteDatabase(), save_name, time_count, time, content_count, content_locate, content_red, content_green, content_blue, content_alpha, content);
						MainActivity.ShowPopup("저장하기", "저장되었습니다.", TableActivity.this);
					}
				}
			})
		    .setPositiveButton(string.common_use_cancel_btn, null)
		    .show();
		}
		else if( item.getItemId() == 2 ) {
			finish();
		}
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if( keyCode == KeyEvent.KEYCODE_BACK )
			finish();
		return super.onKeyDown(keyCode, event);
	}

}
