package com.example.x_split0511;

import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Balance extends Activity {
	int grppid; 
	int expid; 
	int i = 0;
	String per[] = new String[100];
	String per1[] = new String[100];
	int amt[] = new int[100];
	ListView lstbal;
	int ch=-1,count;
	Button b;
	static Boolean checkboxstate[]=new Boolean[10];
	Database db = new Database(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Arrays.fill(checkboxstate, Boolean.FALSE);
		setContentView(R.layout.activity_balance);
		lstbal = (ListView) findViewById(R.id.listViewSettle);
		b=(Button)findViewById(R.id.btnsettleup);
		b.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				for(int j=0;j<count;j++)
				{
					if(checkboxstate[j]==true)
					{
						db.deleteRecord3(expid, per[j], per1[j]);
						display();
						Toast.makeText(getApplicationContext(),"delete from "+per[j]+ "to "+per1[j]+"amt "+amt[j]+ "expid "+expid, Toast.LENGTH_LONG).show();
						
					}
				}

			}
		});
		Intent b = getIntent();
		grppid = b.getIntExtra("gid",0);
		expid = b.getIntExtra("expid", 0);
		display();


	}
	public void display()
	{
		i = 0;
		db.open();
		Cursor c = db.getRecord3(expid);
		if(c.moveToFirst())
		{
			do
			{
				per[i] = (c.getString(0));
				per1[i] = c.getString(1);
				amt[i] = Integer.parseInt(c.getString(2));
				i++;
				count=i;
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
					v = lin.inflate(R.layout.lv_bal,null);


					holder = new ViewHolder();
					holder.checkBox = (CheckBox)v.findViewById(R.id.checkBox1); ;
					holder.watcher = new CheckBoxWatcher();
					holder.checkBox.setOnCheckedChangeListener(holder.watcher);
					v.setTag(holder);
					// holder.watcher.setTarget(pos);

					TextView t = (TextView) v.findViewById(R.id.textVi1);
					t.setText(per[pos] + " pays " + per1[pos] + " Rs. " + amt[pos]);

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
				CheckBox checkBox;
				CheckBoxWatcher watcher;
			}
			class CheckBoxWatcher implements  OnCheckedChangeListener {
				private int target;

				public void setTarget(int target) {
					this.target = target;
				}

				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					// TODO Auto-generated method stub
					if(arg1)
					{
						checkboxstate[target]=true;
					}
					else
					{
						checkboxstate[target]=false;
						//Toast.makeText(getApplicationContext(),"cb "+target+" fls", Toast.LENGTH_LONG).show();
					}
				}

			}
		};
		lstbal.setAdapter(b);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.balance, menu);
		return true;
	}

}
