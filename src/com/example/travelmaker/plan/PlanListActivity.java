package com.example.travelmaker.plan;

import java.util.ArrayList;

//import andexam.ver4_1.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.ImageButton;
//import android.widget.ListView;
//import android.widget.TextView;
import com.example.travelmaker.tour.gpsinfomain.*;

/**
 * �ֺ� ���� ���� ��� ����Ʈ ��Ƽ��Ƽ
 */
public class PlanListActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan_list);
		
		//* ������ ���� �غ�
				ArrayList<String> arGeneral = new ArrayList<String>();
				arGeneral.add("ù°��");
				arGeneral.add("��°��");
				arGeneral.add("��°��");
				arGeneral.add("��°��");
				//*/
				
				/* �迭�� �غ�
				String[] arGeneral = {"������", "�̼���", "������", "��������"};
				//*/

				// ����� �غ�
				ArrayAdapter<String> Adapter;
				Adapter = new ArrayAdapter<String>(this, 
						android.R.layout.simple_list_item_1, arGeneral);

				// ����� ����
				ListView list = (ListView)findViewById(R.id.list);
				list.setAdapter(Adapter);
			}
		// Ŀ���� �׼ǹ� �̿� Ÿ��Ʋ ����

		// x,y ��ǥ, �ݰ�, ���� ���� Ÿ�� ����
		//Intent intent = getIntent();

	
	}
