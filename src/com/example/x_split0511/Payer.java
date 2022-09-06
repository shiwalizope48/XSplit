package com.example.x_split0511;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Payer extends Activity implements OnClickListener {
	Database db = new Database(this);
	int payid; int i,am,cal=0,n;
	int[] flag1 = new int[100];
	ListView palst;
	View v1;
	EditText editText;
	Button done;
	String str,str1;
	int memberid[] = new int[100];
	int h = 0;
	String membername[] = new String[100];
	int amt[] = new int[100];
	int[] arr= new int[100];
	int count;
	TextView  t1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payer);
		for(int i=0;i<100;i++)
			flag1[i]=0;
		
	//	t1 = (TextView) findViewById(R.id.textVieRemainPay);
		//str1 = "Rs. 0.00 "+" / "+"Rs. "+am;
	//	str1 = "hiiii";
	//	t1.setText(str1);
		Intent pa = getIntent();
		payid =  pa.getIntExtra("grp-id", 0);
		am =  pa.getIntExtra("exp", 0);
		n=am;
		palst = (ListView) findViewById(R.id.listViewpay);
		display();
		done = (Button) findViewById(R.id.btnaddpay);
		done.setOnClickListener(this);
		
	}
	 public void display()
	 {	
		 	i = 0; count = 0;
	  		db.open();
    		Cursor c = db.getRecord1(payid);
    		if(c.moveToFirst())
    		{
    			do
    			{
    				memberid[i] = Integer.parseInt(c.getString(0));
    				membername[i] = c.getString(1);
    				i++;
    				count++;
    			}
    			while(c.moveToNext());
    		}

    		BaseAdapter b = new BaseAdapter() {
        			@Override
        			public View getView(int pos, View v, ViewGroup arg2) {
        				ViewHolder holder;
        				//View v = null;
        				if (v == null) {

        					// TODO Auto-generated method stub
        					LayoutInflater lin = getLayoutInflater();
        					//v = lin.inflate(R.layout.lv_payer,null);
        					v = lin.inflate(R.layout.lv_payer, arg2, false);
        					/*TextView t1 = (TextView) v.findViewById(R.id.textVieRemainPay);
        					t1.setText("Rs. "+n+" / "+"Rs. "+am);	*/	
        					
        					
        					holder = new ViewHolder();
        					holder.editText = (EditText)v.findViewById(R.id.atomPay_value); ;
        					holder.watcher = new EditTextWatcher();
        					holder.editText.addTextChangedListener(holder.watcher);
        					v.setTag(holder);
        					// holder.watcher.setTarget(pos);
        					/*	View view = palst.getChildAt(pos);
        				editText =(EditText) view.findViewById(R.id.atomPay_value);
        				amt[h] = Integer.parseInt(editText.getText().toString());*/
        		/*			n-=Integer.parseInt(holder.editText.getText().toString());
        					TextView t1 = (TextView) v.findViewById(R.id.textVieRemainPay);
        					t1.setText("Rs. "+n+" / "+"Rs. "+am);*/
        					
        					TextView t = (TextView) v.findViewById(R.id.textVi1);
        					t.setText(membername[pos]);
        				}
        				else {
        					holder = (ViewHolder) v.getTag();
        				}
        				holder.watcher.setTarget(pos);

        				//holder.editText.setText(items.get(pos));
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
        			class ViewHolder {
        				EditText editText;
        				EditTextWatcher watcher;
        			}
        			class EditTextWatcher implements TextWatcher {

        				private int target;

        				public void setTarget(int target) {
        					this.target = target;
        				}

        				@Override
        				public void afterTextChanged(Editable s) {
        					//items.set(target, s.toString());
        						
        					amt[target]=Integer.parseInt(s.toString());
        					//Toast.makeText(getApplicationContext(),"val " +s.toString()+"t "+target, Toast.LENGTH_LONG).show();
        				/*	if(flag1[target] !=0)
        					{
        						
        						n-=amt[target];
            					Toast.makeText(getApplicationContext(),"Remain  "+n, Toast.LENGTH_LONG).show();
            			//		t1.setText("Rs. "+n+" / "+"Rs. "+am);
            			//		str = "Rs. "+n+" / "+"Rs. "+am;
            			//		t1.setText(str);
            					
        					}	
        					flag1[target]=1;*/
        					
        				}

        				@Override
        				public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        				}

        				@Override
        				public void onTextChanged(CharSequence s, int start, int before, int count) {
        						
        				}

        			}
        		};

    		palst.setAdapter(b);
        }
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.payer, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.addPayer:/*
        	Intent i = new Intent(this,CrtExp.class);
            startActivity(i);*/
			
            return true;
        default:
		return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		for(int i=0;i<count;i++)
		{
			cal+=amt[i];
		}
		if(am == cal)
		{
		 for (int i = 0 ; i < count; i++) 
		 {
//	           Toast.makeText(getApplicationContext(),""+amt[i], Toast.LENGTH_LONG).show();
		 }
	          
		Intent returnIntent = new Intent();
		returnIntent.putExtra("id", payid);
		returnIntent.putExtra("Amt array", amt);
		returnIntent.putExtra("member names", membername);
		returnIntent.putExtra("count", count);
	//	Toast.makeText(getBaseContext(), "in here"+payid, Toast.LENGTH_LONG).show();
		setResult(RESULT_OK,returnIntent);
	//	Toast.makeText(getBaseContext(), "after set", Toast.LENGTH_LONG).show();
		finish();
		}
		else
		{
			Toast.makeText(getApplicationContext(),""+"Enter Correct Entries  "+cal, Toast.LENGTH_LONG).show();
			
			for(int i=0;i<count;i++)
			{
				amt[i]=0;
			}
			cal=0;
	//		n = am;
			display();
		}
		
	}
/*	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		amt[h] = Integer.parseInt(editText.getText().toString());
		Toast.makeText(getBaseContext(), "arr" + amt[h], Toast.LENGTH_LONG).show();
	}
	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}*/


}
