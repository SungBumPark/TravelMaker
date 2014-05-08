package com.example.travelmaker.tourinfo.network;

import android.util.*;

import com.example.travelmaker.areainfo.network.*;
import com.example.travelmaker.main.*;

public class COMNFeedParserFactory {

	static String myKey = "MJkp3LoVgTkustkN/LPkOFCJ7b1lOaZiQTlN4PeoLS2lRwvBzKKqXsLy0uP5ltAvk9aiXSAx5nQE7FqshJaReg==";
	static String ServiceKey;
    static String feedUrl ; 
    
    
    /**
     * ������ URL�κ��� �����͸� �о���� �ļ��� �����Ͽ� ��ȯ�Ѵ�.
     * 
     * @param params
     *            - URL�� ����µ� ���� �Ķ����
     * @return ������ �ļ�
     */
    public static FeedParser3 getParser(String... params) {
    	Log.d(GPSInfoMain.DEBUG, "#7");
    	return new COMNTourDataSAXParser(createUrl(params));
    }
    
    /**
     * �Ķ���͸� �̿��Ͽ� URL�� ����� ��ȯ�Ѵ�.
     * 
     * @param params
     *            URL�� ����µ� ���� �Ķ����
     * @return ������ URL String
     */
    
 
    @SuppressWarnings("deprecation")
	public static String createUrl(String... params) {
    	//ServiceKey = URLEncoder.encode(myKey);
    	//ServiceKey = "dZSY4J8atDXUPgljqgKgff%2BBpa%2BvdLvFivU%2BBl8YTIbtAk8JVUVVyLyO%2BGMY%2FEBaQa2EiGnTnw6Ol5zphVRstA%3D%3D";
    	feedUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey="
    	+ myKey + "&contentTypeId=" + params[0] + "&contentId=" + params[1]
    			+ "&MobileOS=AND&MobileApp=TravelMaker&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y";
    	Log.d(GPSInfoMain.DEBUG,
			    "#8   " + feedUrl);
    	
    	
    	
    	return feedUrl;
    }
}