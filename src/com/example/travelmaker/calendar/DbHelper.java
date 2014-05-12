package com.example.travelmaker.calendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "testtest";
	private static final int DB_VERSION = 1;
	
	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		String sql = "create table sangpum("+
				"_id integer primary key autoincrement,"+
				"code text not null,"+
				"dpday text not null,"+
				"dptime text not null,"+
				"chday text not null,"+
				"chtime text not null)";
		
		db.execSQL(sql);
		
	}//onCreate  테이블만들기
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exist sangpum");
		onCreate(db);
		
	}

}
