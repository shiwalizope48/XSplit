package com.example.x_split0511;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Contacts1 extends Activity {
	Database db = new Database(this);
	int id[] = new int[3000];
	String contactname[] = new String[3000];
	int contactno[] = new int[3000];
	String selcontactnm[]= new String[3000];
	int i = 0;
	int j = 0;
	ListView lstvw1;
	BaseAdapter b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts1);
		lstvw1 = (ListView)findViewById(R.id.listView2);
		Intent y = getIntent();
		selcontactnm = y.getStringArrayExtra("array");
		db.open();
		Cursor c = db.getAllRecord4();
		if(c.moveToFirst())
		{
			do
			{
				id[i] = Integer.parseInt(c.getString(0));
				contactname[i] = c.getString(1);
			//	contactno[i] = Integer.parseInt(c.getString(2));
				i++;
			}
			while(c.moveToNext());
		}
		b = new BaseAdapter() {
			
			@Override
			public View getView(int pos, View arg1, ViewGroup arg2) {
				// TODO Auto-generated method stub
				LayoutInflater lin = getLayoutInflater();
				View v = lin.inflate(R.layout.lstlay,null);
				TextView t = (TextView) v.findViewById(R.id.lst1);
				t.setText(contactname[pos]);
				return v;
			}
			
			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return arg0;
			}
			
			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return i+1;
			}
		};
		lstvw1.setAdapter(b);
		lstvw1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
		//		Toast.makeText(getBaseContext(),"pos =" + pos, Toast.LENGTH_LONG).show();
			/*	Intent i = new Intent(getBaseContext(),CrtGrp.class);
				selcontactnm[j] = contactname[pos];
				i.putExtra("sentarray", selcontactnm);
				i.putExtra("count", j);
				j++;
				*/
				//selcontactnm[j] = contactname[pos];
				Intent returnIntent = new Intent();
				returnIntent.putExtra("sent", contactname[pos]);
				returnIntent.putExtra("id", id[pos]);
			//	returnIntent.putExtra("count", j+1);
				//j++;
				setResult(RESULT_OK,returnIntent);
				finish();
				
				
				//startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contacts1, menu);
		return true;
	}

}
