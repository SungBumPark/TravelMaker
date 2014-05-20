/**
 * @author Kim Woo Hyeon
 * MainActivity.java
 */

package com.example.travelmaker.timetable;

import java.util.ArrayList;

import com.example.travelmaker.tour.gpsinfomain.R;
import com.example.travelmaker.tour.gpsinfomain.R.string;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {

	private static DataBase mHelper;
	private static SQLiteDatabase mDb;
	private int mSelectedItem = -1;
	private String mListItems[] = null;
	private static Drawable mBackgroundImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Initialize;
		Init();
		// Enroll listener;
		SetListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void onClose() {
		// TODO Auto-generated method stub
		finish();
		android.os.Process.killProcess( android.os.Process.myPid() );
	}
	
	@SuppressWarnings("deprecation")
	private void Init() {
		// TODO Auto-generated method stub
		mBackgroundImg = getResources().getDrawable(R.drawable.bg);
		((RelativeLayout) findViewById(R.id.mainBackground)).setBackgroundDrawable( mBackgroundImg );
		mHelper = new DataBase(this);
		try {
			mDb = mHelper.getWritableDatabase();
		} catch (SQLiteException e) {
			mDb = mHelper.getReadableDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( mHelper.selectAll( mDb ) == null )
		{
			mHelper.onUpgrade( mDb, 0, 0 );
		}
	}
	
	private void SetListener() {
		// TODO Auto-generated method stub
		((Button) findViewById(R.id.makeBtn)).setOnClickListener(this);
		((Button) findViewById(R.id.loadBtn)).setOnClickListener(this);
		((Button) findViewById(R.id.deleteBtn)).setOnClickListener(this);
		((Button) findViewById(R.id.bgChangeBtn)).setOnClickListener(this);
		((Button) findViewById(R.id.helpBtn)).setOnClickListener(this);
		((Button) findViewById(R.id.exitBtn)).setOnClickListener(this);
	}
	
	public static DataBase GetDatabase() {
		// TODO Auto-generated method stub
		return mHelper;
	}
	
	public static SQLiteDatabase GetSQLiteDatabase() {
		// TODO Auto-generated method stub
		return mDb;
	}
	
	public static void ShowPopup( String title, String msg, Context context ) {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(context)
		.setIcon(R.drawable.ic_launcher)
		.setTitle(title)
	    .setMessage(msg)
		.setPositiveButton(string.common_use_ok_btn, null)
		.show();
	}
	
	public static Drawable GetBackgroundImage() {
		// TODO Auto-generated method stub
		return mBackgroundImg;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch( requestCode )
		{
		case define.INTENT_REQUEST_EXCHANGEBG:
			if( resultCode == define.INTENT_RESULT_SUCCESS )
			{
				String Path = ExplorerActivity.GetSelectedFilePath();

				BitmapFactory.Options bfo = new BitmapFactory.Options();
				bfo.inSampleSize = 2;

				Bitmap bm = BitmapFactory.decodeFile(Path, bfo);

				mBackgroundImg =new BitmapDrawable(bm);
				((RelativeLayout) findViewById(R.id.mainBackground)).setBackgroundDrawable( mBackgroundImg );
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch( v.getId() )
		{
		case R.id.makeBtn:
		{
			define.debug("makeTableBtn Click!");
			Intent intent = new Intent( this, MakeActivity.class );
			startActivity(intent);
		}
			break;
		case R.id.loadBtn:
		{
			define.debug("loadTableBtn Click!");
			mSelectedItem = -1;
			ArrayList<String> items = new ArrayList<String>();
			Cursor cursor = mHelper.selectName(mDb);
			while( cursor.moveToNext() )
				items.add( cursor.getString(0) );
			if( items.size() == 0 )
			{
				MainActivity.ShowPopup("불러오기", "저장된 값이 없습니다.", MainActivity.this);
				break;
			}
			mListItems = new String[items.size()];
			for( int idx = 0; idx < mListItems.length; ++idx )
				mListItems[idx] = items.get(idx);
			new AlertDialog.Builder(this)
			.setIcon(R.drawable.ic_launcher)
			.setTitle("불러오기")
			.setNegativeButton(string.common_use_ok_btn, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					if( mSelectedItem == -1 )
						MainActivity.ShowPopup("불러오기", "선택하지 않았습니다.", MainActivity.this);
					else
					{
						ArrayList<String> items = new ArrayList<String>();
						Cursor cursor = mHelper.selectInfoForName(mDb, mListItems[mSelectedItem]);
						while( cursor.moveToNext() )
						{
							items.add( cursor.getString(0) );		// index
							items.add( cursor.getString(1) );		// save_name
							items.add( cursor.getString(2) );		// time_count
							items.add( cursor.getString(3) );		// time
							items.add( cursor.getString(4) );		// content_count
							items.add( cursor.getString(5) );		// content_locate
							items.add( cursor.getString(6) );		// content_red
							items.add( cursor.getString(7) );		// content_green
							items.add( cursor.getString(8) );		// content_blue
							items.add( cursor.getString(9) );		// content_alpha
							items.add( cursor.getString(10) );		// content
						}
						
						Intent intent = new Intent( MainActivity.this, com.example.travelmaker.timetable.TableActivity.class );
						intent.putExtra( define.INTENT_KEY_LOAD_START, true );
						intent.putExtra( define.INTENT_KEY_TIMECOUNT, Integer.parseInt(items.get(2)) );
						intent.putExtra( define.INTENT_KEY_LOAD_TIME, items.get(3) );
						intent.putExtra( define.INTENT_KEY_CONTENT_COUNT, items.get(4) );
						intent.putExtra( define.INTENT_KEY_CONTENT_LOCATE, items.get(5) );
						intent.putExtra( define.INTENT_KEY_CONTENT_RED, items.get(6) );
						intent.putExtra( define.INTENT_KEY_CONTENT_GREEN, items.get(7) );
						intent.putExtra( define.INTENT_KEY_CONTENT_BLUE, items.get(8) );
						intent.putExtra( define.INTENT_KEY_CONTENT_ALPHA, items.get(9) );
						intent.putExtra( define.INTENT_KEY_CONTENT, items.get(10) );
						startActivity(intent);
					}
				}
				
			})
			.setPositiveButton(string.common_use_cancel_btn, null)
			.setSingleChoiceItems( mListItems, -1, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					mSelectedItem = which;
				}
				
			})
			.show();
		}
			break;
		case R.id.deleteBtn:
		{
			define.debug("deleteTableBtn Click!");
			mSelectedItem = -1;
			ArrayList<String> items = new ArrayList<String>();
			Cursor cursor = mHelper.selectName(mDb);
			while( cursor.moveToNext() )
				items.add( cursor.getString(0) );
			if( items.size() == 0 )
			{
				MainActivity.ShowPopup("삭제하기", "저장된 값이 없습니다.", MainActivity.this);
				break;
			}
			mListItems = new String[items.size()];
			for( int idx = 0; idx < mListItems.length; ++idx )
				mListItems[idx] = items.get(idx);
			new AlertDialog.Builder(this)
			.setIcon(R.drawable.ic_launcher)
			.setTitle("삭제하기")
			.setNegativeButton(string.common_use_ok_btn, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					if( mSelectedItem == -1 )
						MainActivity.ShowPopup("삭제하기", "선택하지 않았습니다.", MainActivity.this);
					else
					{
						mHelper.deleteName(mDb, mListItems[mSelectedItem]);
						MainActivity.ShowPopup("삭제하기", "삭제하였습니다.", MainActivity.this);
					}
				}
			})
			.setPositiveButton(string.common_use_cancel_btn, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			})
			.setSingleChoiceItems( mListItems, -1, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					mSelectedItem = which;
				}
			})
			.show();
		}
			break;
		case R.id.bgChangeBtn:
		{
			Intent intent = new Intent(this, ExplorerActivity.class);
			startActivityForResult(intent, define.INTENT_REQUEST_EXCHANGEBG);
		}
			break;
		case R.id.helpBtn:
		{
			define.debug("helpBtn Click!");
			Intent intent = new Intent( this, HelpActivity.class );
			startActivity(intent);
		}
			break;
		case R.id.exitBtn:
		{
			define.debug("exitBtn Click!");
			onClose();
		}
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
			onClose();
		}
		return super.onKeyDown(keyCode, event);
	}

}
