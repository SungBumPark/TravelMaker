package com.example.travelmaker.post;
import com.example.travelmaker.tour.gpsinfomain.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PostMainActivity extends Activity{

	private WebView mWebView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_main);

		setLayout();

		// 웹뷰에서 자바스크립트실행가능
		mWebView.getSettings().setJavaScriptEnabled(true); 
		// 여행 후기 게시판
		mWebView.loadUrl("http://melo07.dothome.co.kr/xe/board_DIARY");
		// WebViewClient 지정
		mWebView.setWebViewClient(new WebViewClientClass());  
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) { 
			mWebView.goBack(); 
			return true; 
		} 
		return super.onKeyDown(keyCode, event);
	}

	private class WebViewClientClass extends WebViewClient { 
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) { 
			view.loadUrl(url); 
			return true; 
		} 
	}
	private void setLayout(){
		mWebView = (WebView) findViewById(R.id.webview);
	}
}

