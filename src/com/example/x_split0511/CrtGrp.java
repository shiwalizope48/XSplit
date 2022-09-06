package com.example.x_split0511;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CrtGrp extends Activity {
	Database db = new Database(this);
	EditText grpnm;
	String valid_name= null;
	String conname;
	BaseAdapter b;
	int arrcnt;
	int grpid[] = new int[100];
	int memberid[] = new int[100];
	String grpContacts[] = new String[100];
	ListView lstvw2;
	int count, k = 0;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crt_grp);
//		Toast.makeText(getBaseContext(), "on create back", Toast.LENGTH_LONG).show();
		
		lstvw2 = (ListView)findViewById(R.id.lvw);
		
		grpnm = (EditText)findViewById(R.id.editGropName);
	//	Is_Valid_Person_Name(grpnm);
	/*	grpnm.addTextChangedListener(new TextWatcher() {	
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Is_Valid_Person_Name(grpnm);
			}
		});*/
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	
		 if (requestCode == 1) {
		        if(resultCode == RESULT_OK){
		        
		            //String  = data.getStringExtra("sentarray");
		        	grpContacts[k] = data.getStringExtra("sent");
		        	memberid[k] = data.getIntExtra("id", 0);
		        	//count = data.getIntExtra("count", 0);
		        	k++;
		        	arrcnt = k;
		       // 	Toast.makeText(getBaseContext(), "back"+grpContacts[0], Toast.LENGTH_LONG).show();
		        //	Toast.makeText(getBaseContext(), "back"+grpContacts[1], Toast.LENGTH_LONG).show();
		        
		        	//Toast.makeText(getBaseContext(), "back"+grpContacts[2], Toast.LENGTH_LONG).show();
		        	lstdisp(grpContacts,k-1);
		        }
		    
		 }   
	}
	public void lstdisp(final String[] arr,final int j)
	{
	    b = new BaseAdapter() {
			
			
			@Override
			public View getView(int pos, View arg1, ViewGroup arg2) {
				// TODO Auto-generated method stub
				LayoutInflater lin = getLayoutInflater();
				View v = lin.inflate(R.layout.lstlay,null);
				TextView t = (TextView) v.findViewById(R.id.lst1);
				t.setText(arr[pos]);
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
				return j+2;
			}
		};
		lstvw2.setAdapter(b);
	
 }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crt_grp, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	    switch (item.getItemId()) {
	        case R.id.done:
	        	
	        	if(memberid[0]!=0 && !grpnm.equals("")){
	        		//String gn= grpnm.getText().toString();
	        		db.open();
	        		long id = db.insertRecord(grpnm.getText().toString());
	        		db.close();
	        		db.open();
	        		Cursor c = db.getRecord(grpnm.getText().toString());
	        		//			Toast.makeText(getBaseContext(), ""+ c.getString(0), Toast.LENGTH_LONG).show();
	        		grpnm.setText("");
	        		for(int g = 0; g < arrcnt; g++)
	        		{
	        			id = db.insertRecord1(Integer.parseInt(c.getString(0)), memberid[g], grpContacts[g]);
	        		}
	        		Intent i = new Intent(this,AddGroup.class);
	        		startActivity(i);
	        		//  i.putExtra("gr",gn);
	        	}
	        	else
	        	{
	        		if(grpnm.equals(""))
	        		{
	        			Toast.makeText(getBaseContext(), "Enter Name!!!", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(memberid[0] == 0)
	        		{
	        			Toast.makeText(getBaseContext(), "Add Person in Group!!!", Toast.LENGTH_SHORT).show();
	        		}
	        		
	        	}
	            return true;
	        case R.id.addpers:
	        	Intent i1 = new Intent(this,Contacts1.class);
	        	i1.putExtra("array", grpContacts);
	        	/*i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    		i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);                  
	    		i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);*/
	            startActivityForResult(i1, 1);
	            return true;
	        case android.R.id.home:
		    	 Intent homeIntent = new Intent(this, AddGroup.class);
		            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		            startActivity(homeIntent);
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void Is_Valid_Person_Name(EditText edt) throws NumberFormatException {
		if (edt.getText().toString().length() <= 0) {
			edt.setError("Accept Alphabets Only.");
			valid_name=null;
		} else if (!edt.getText().toString().matches("([a-zA-Z ]*+[0-9]*)*")) {
			edt.setError("Accept Alphabets Only.");
			valid_name=null;
		} else {
			valid_name = edt.getText().toString();
		}

	}
	
	private Boolean exit = false;
	@Override
	    public void onBackPressed() {
	        if (exit)
	        {
	        	System.exit(0);
	        	
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