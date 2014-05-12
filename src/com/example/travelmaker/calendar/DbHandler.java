package com.example.travelmaker.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DbHandler {
	private Context ctx;
	private DbHelper helper;
	private SQLiteDatabase db;
	
	public DbHandler(Context ctx){
		this.ctx = ctx;
		helper = new DbHelper(ctx); //db¿Í table »ý¼º,upgrade 
		db = helper.getWritableDatabase();
	}
	public static DbHandler open(Context ctx) throws SQLException{
		DbHandler handler = new DbHandler(ctx);
		return handler;
	}
	public void close(){
		helper.close();
	}
	public long insert(String code, String dpday, String dptime, String chday, String chtime){
		ContentValues values = new ContentValues();
		values.put("code", code);
		values.put("dpday", dpday);
		values.put("dptime", dptime);
		values.put("chday", chday);
		values.put("chtime", chtime);
		long result= db.insert("sangpum",null, values);
		return result;
	}//insert
	public Cursor selectAll() {
		Cursor cursor = db.query(true, "sangpum", new String[] {"_id","code","dpday","dptime","chday","chtime"},null, null, null, null, null,null);
		return cursor;
	}	
}