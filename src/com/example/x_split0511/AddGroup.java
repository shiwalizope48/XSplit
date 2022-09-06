package com.example.x_split0511;

import java.io.File;

import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddGroup extends Activity {
	Database db;
	String grpname[] = new String[100];
	int grpid[] = new int[100];
	int i;
	BaseAdapter b;
	int flg=0;
	ListView lstvw;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_group);
		lstvw = (ListView)findViewById(R.id.listView2);
		try
        {
        	String destpath = "/data/data/" + getPackageName() + "/databases/X_Split_DB";
        	File f = new File(destpath);
        	if(/*true*/!f.exists())
        	{
            	flg=1;
    	  	//	Toast.makeText(this, "in does not exit", Toast.LENGTH_LONG).show();
    	  		//////fetch contacts activity call
    	  		//fr.launchRingDialog(this);

    	  		db = new Database(this);
    	  		final ProgressDialog ringProgressDialog = ProgressDialog.show(this, 
    	  				"Please wait ...", "Retrieving Contacts of your phone... It may take time", true);
    	  		ringProgressDialog.setCancelable(false);
    	  		new Thread(new Runnable() {
    	  			@Override
    	  			public void run() {
    	  				try {
    	  					// Here you should write your time consuming task...
    	  					//new code start

    	  					ContentResolver cr = getContentResolver();
    	  					Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
    	  							null, null, null,ContactsContract.Contacts.DISPLAY_NAME + " ASC ");
    	  					if (cur.getCount() > 0) {
    	  						while (cur.moveToNext()) {
    	  							String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
    	  							String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
    	  							if (Integer.parseInt(cur.getString(
    	  									cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

    	  								Cursor pCur = cr.query(
    	  										ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
    	  										null,
    	  										ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",new String[]{id}, null);
    	  								while (pCur.moveToNext()) {
    	  									String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
    	  									db.open();
    	  									db.insertRecord4(name,phoneNo);
    	  								}
    	  								db.close();
    	  								pCur.close();
    	  							}
    	  						}
    	  					}

    	  		//			Toast.makeText(getBaseContext(), "done", Toast.LENGTH_SHORT).show();
    	  					///new code end
    	  					// Let the progress ring for 1 seconds...

    	  					Thread.sleep(1000);
    	  				} catch (Exception e) {}

    	  				ringProgressDialog.dismiss();
    	  			}
    	  		}).start();
        	}
        	if(flg==0){
    	  		db = new Database(this);  		
    	  	}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		db.open();
		Cursor c = db.getAllRecord();
		if(c.moveToFirst())
		{
			do
			{
				grpid[i] = Integer.parseInt(c.getString(0));
				grpname[i] = c.getString(1);
				i++;
			}
			while(c.moveToNext());
		}
	//	Toast.makeText(this,"grpname = " + grpname[0], Toast.LENGTH_LONG).show();
		b = new BaseAdapter() {
			
			@Override
			public View getView(int pos, View arg1, ViewGroup arg2) {
				// TODO Auto-generated method stub
				LayoutInflater lin = getLayoutInflater();
				View v = lin.inflate(R.layout.lstlay,null);
				TextView t = (TextView) v.findViewById(R.id.lst1);
				t.setText(grpname[pos]);
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
				return i;
			}
		};
		lstvw.setAdapter(b);
		lstvw.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
	//			Toast.makeText(getBaseContext(),"pos =" + pos, Toast.LENGTH_LONG).show();
				Intent u = new Intent(getBaseContext(), AddExp.class);
				u.putExtra("id value", grpid[pos]);
				startActivity(u);
			}
		});
		lstvw.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "delete successfull" + pos, 1500).show();
				db.open();
				if(db.deleteRecord(grpid[pos]))
				{
					Toast.makeText(getBaseContext(), "delete successfull", 1500).show();
				}
				else
					Toast.makeText(getBaseContext(), "delete failed", 1500).show();
				
				return false;
			}
		});
		/*db.open();
		Cursor c = db.getAllRecord();
		if(c.moveToFirst())
		{
			do
			{
				grpid[i] = Integer.parseInt(c.getString(0));
				grpname[i] = c.getString(1);
				i++;
			}
			while(c.moveToNext());
		}*/
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_group, menu);
		return true;
	}
		@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
	    switch (item.getItemId()) {
	    	case R.id.addgrp:
	    		Intent i = new Intent(this,CrtGrp.class);
	    		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);                  
	    		i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	    		//i.putExtra("group id", grpid);
	    		startActivity(i);
	    		return true;
	    	default:
	    		return super.onOptionsItemSelected(item);
	    }
	}
	private Boolean exit = false;
	@Override
    public void onBackPressed() {
        if (exit){
        	Intent startMain = new Intent(Intent.ACTION_MAIN);
			startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            finish();  
        }
        else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }

    }

}
