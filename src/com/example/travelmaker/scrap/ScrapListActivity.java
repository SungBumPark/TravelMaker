package com.example.travelmaker.scrap;

import java.util.*;

import com.example.travelmaker.calendar.*;
import com.example.travelmaker.info.adapter.*;
import com.example.travelmaker.info.data.*;
import com.example.travelmaker.main.*;
import com.example.travelmaker.tour.gpsinfomain.*;

import android.app.*;
import android.content.*;
import android.database.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.AbsListView.*;

public class ScrapListActivity extends ListActivity implements OnClickListener, OnScrollListener{

	
	DbHandler dbHandler;
	Cursor cursor = null;
	private CustomApplication application;
	private ArrayList<ScrapTourData> ScrapList;
	private ScrapAdapter adapter;
	private ImageButton ibPre;
	private TextView listTitle;
	private View progressDialog;
	private ArrayList<String> titleList;
	ArrayList<ScrapTourData> dataList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d(GPSInfoMain.DEBUG, "x1");
		setContentView(R.layout.activity_tour_list);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.title_with_button);
		Log.d(GPSInfoMain.DEBUG, "x2");
		
		progressDialog = findViewById(R.id.progressDialog);
		progressDialog.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return true;
			}
		});

		/*new String[] {"title","contentId","homepage","imageUrl", 
		"contentTypeId", "addr1", "addr2", 
		"overview", "tel", "zipcode", "EX", "EY"},*/
		
		dataList = new ArrayList<ScrapTourData>();
		Log.d(GPSInfoMain.DEBUG, "x3");
		ibPre = (ImageButton) findViewById(R.id.ib_pre);
		ibPre.setOnClickListener(this);
		listTitle = (TextView) findViewById(R.id.list_title);
		Log.d(GPSInfoMain.DEBUG, "x4");
		
		getListView().setOnScrollListener(this);
		
		Log.d(GPSInfoMain.DEBUG, "x5");
		dbHandler = DbHandler.open(this);
		Log.d(GPSInfoMain.DEBUG, "x6");
		cursor = dbHandler.selectAll("scrap");
		
		Log.d(GPSInfoMain.DEBUG, "x7 " + cursor.getCount());
				
		int count = 0;
		while (cursor.moveToNext()) {

			final ScrapTourData currentData = new ScrapTourData();
			String title = cursor.getString(0);
			Log.d(GPSInfoMain.DEBUG, cursor.getString(0));
			String contentId = cursor.getString(1);
			String homepage = cursor.getString(2);
			String imageUrl = cursor.getString(3);
			String contentTypeId = cursor.getString(4);
			String addr1 = cursor.getString(5);
			String addr2 = cursor.getString(6);
			String overview = cursor.getString(7);
			String tel = cursor.getString(8);
			String zipcode = cursor.getString(9);
			String EX = cursor.getString(10);
			String EY = cursor.getString(11);
			
			currentData.setTitle(title);
			currentData.setContentId(contentId);
			currentData.setHomepage(homepage);
			currentData.setImageUrl(imageUrl);
			currentData.setContentTypeId(contentTypeId);
			currentData.setAddr1(addr1);
			currentData.setAddr2(addr2);
			currentData.setOverview(overview);
			currentData.setTel(tel);
			currentData.setZipcode(zipcode);
			currentData.setEX(EX);
			currentData.setEY(EY);
			
			Log.d(GPSInfoMain.DEBUG, currentData.getTitle());
			Log.d(GPSInfoMain.DEBUG, currentData.getContentId());
			Log.d(GPSInfoMain.DEBUG, currentData.getImageUrl());
			Log.d(GPSInfoMain.DEBUG, currentData.getEX());
			Log.d(GPSInfoMain.DEBUG, currentData.getEY());
			Log.d(GPSInfoMain.DEBUG, currentData.getHomepage());
			Log.d(GPSInfoMain.DEBUG, currentData.getZipcode());
			
			count++;
			dataList.add(currentData);
		}
		
		listTitle.setText("총 " + count + "곳이 검색되었습니다.");
		for(ScrapTourData tourData : dataList)
			Log.d(GPSInfoMain.DEBUG, "y1 " + tourData.getTitle());
		
		titleList = new ArrayList<String>();
		for (ScrapTourData scrapData : dataList){
			titleList.add(scrapData.getTitle());
			Log.d(GPSInfoMain.DEBUG, "y2 " + scrapData.getTitle());
		}
		
		Log.d(GPSInfoMain.DEBUG, "x8");
		adapter = new ScrapAdapter(dataList, this);
		
		setListAdapter(adapter);
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (position >= titleList.size())
			return;

		showProgressDialog();
		

		Intent intent = new Intent(this, ScrapInfoView.class);
		intent.putExtra("title", dataList.get(position).getTitle());
		intent.putExtra("contentId", dataList.get(position).getContentId());
		Log.d(GPSInfoMain.DEBUG, "x9 " + dataList.get(position).getContentId());
		intent.putExtra("homepage", dataList.get(position).getHomepage());
		intent.putExtra("imageUrl", dataList.get(position).getImageUrl());
		intent.putExtra("contentTypeId", dataList.get(position).getContentTypeId());
		intent.putExtra("addr1", dataList.get(position).getAddr1());
		intent.putExtra("addr2", dataList.get(position).getAddr2());
		intent.putExtra("overview", dataList.get(position).getOverview());
		intent.putExtra("tel", dataList.get(position).getTel());
		intent.putExtra("zipcode", dataList.get(position).getZipcode());
		intent.putExtra("EX", dataList.get(position).getEX());
		intent.putExtra("EY", dataList.get(position).getEY());
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				hideProgressDialog();
			}

			//Do Something 1000 = 1s
		}, 1000);
		
		startActivity(intent);
	}
	
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ib_pre:
			finish();
			break;
		}
	}

	/**
	 * 프로그레스바르 보여준다
	 */
	public void showProgressDialog() {
		progressDialog.setVisibility(View.VISIBLE);
	}

	/**
	 * 프로그레스바를 감춘다
	 */
	public void hideProgressDialog() {
		progressDialog.setVisibility(View.GONE);
	}

	
}
