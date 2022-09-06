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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CrtExp extends Activity {
	String valid_name=null;
	EditText expnm,exp;
	int getid;
	Database db = new Database(this);
	Spinner equn;
	BaseAdapter b;
	String arr[] = new String[2];
	Intent e1;
	Button paidby;
	String membername[] = new String[50];
	int paidbyamt[] = new int[50];
	int shareamt[] = new int[50];
	int no_member;
	String[][] Sol = new String[50][3];


	int i,pay,insh,j=0,calc=0,value=0,index,h=0,k=0,tk=0,l,cnt,cntf,flag;
	//	int[] payarray = new int[50];
	//	int[] indsh = new int[50];
	int[] arr1 = new int[50];
	int[] array = new int[50];
	int[] giving = new int[50];
	int[] take = new int[50];
	//	String[] name = new String[50];
	String[][] Fin = new String[50][3];

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crt_exp);
		arr[0] = "Equally";
		arr[1] = "Unequally";
		e1 = getIntent();
		getid = e1.getIntExtra("value", 0);
		expnm = (EditText)findViewById(R.id.addExp);
		exp = (EditText)findViewById(R.id.addRs);
		equn = (Spinner)findViewById(R.id.spnr2);
		paidby = (Button) findViewById(R.id.btnPaidBy);
		paidby.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if(!exp.getText().toString().equals(""))
				{
					Intent ip = new Intent(getBaseContext(),Payer.class);
					ip.putExtra("grp-id", getid);
					ip.putExtra("exp", Integer.parseInt(exp.getText().toString()));
					startActivityForResult(ip, 16);
				}
				else
				{
					Toast.makeText(getBaseContext(), "Enter Expense Amount!!!", Toast.LENGTH_LONG).show();
				}
			}
		});
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
				return 2;
			}
		};
		equn.setAdapter(b);
		equn.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				// TODO Auto-generated method stub
				/*if(pos == 0)
				{
					flag=1;
				}*/

				if(!exp.getText().toString().equals(""))
				{
					if(pos == 1)
					{
						flag =2;
						Intent ex = new Intent(getBaseContext(),UneShareActivity.class);
						ex.putExtra("grp id for share", getid);
						ex.putExtra("exp", Integer.parseInt(exp.getText().toString()));
						//startActivityForResult(ex, 45);
						startActivityForResult(ex,21);
					}
				}
				else
				{
					Toast.makeText(getBaseContext(), "Enter Expense!!!", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		/*		expnm.addTextChangedListener(new TextWatcher() {

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
				Is_Valid_Person_Name(expnm,1);
			}
		});
		exp.addTextChangedListener(new TextWatcher() {

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
				Is_Valid_Person_Name(exp,2);
			}
		});*/
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 16)
		{
			if(resultCode == RESULT_OK)
			{

				//		Toast.makeText(getBaseContext(), "back", Toast.LENGTH_LONG).show();
				getid = data.getIntExtra("id", 0);
				no_member = data.getIntExtra("count", 0);
				paidbyamt = data.getIntArrayExtra("Amt array");
				membername = data.getStringArrayExtra("member names");
				//		Toast.makeText(getBaseContext(), "back" + getid, Toast.LENGTH_LONG).show();
				/*for(int j = 0; j < no_member; j++)
				{
					Toast.makeText(getBaseContext(), "back" + "\n"+"amt" + paidbyamt[j] + "\nmember" + membername[j], Toast.LENGTH_LONG).show();
				}*/
			}
		}
		if(requestCode == 21)
		{
			if(resultCode == RESULT_OK)
			{
				getid = data.getIntExtra("id", 0);
				no_member = data.getIntExtra("count", 0);
				shareamt = data.getIntArrayExtra("Amt array");
				membername = data.getStringArrayExtra("member names");
				/*for(int j = 0; j < no_member; j++)
				{
					Toast.makeText(getBaseContext(), "back" + "\n"+"amt\t" + shareamt[j] + "\nmember\t" + membername[j], Toast.LENGTH_LONG).show();
				}*/
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crt_exp, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//UnMul u = new UnMul();
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.donecrtexp:
		{
			if(!expnm.equals(""))
			{
				db.open();
				long id = db.insertRecord2(getid,expnm.getText().toString());

				Cursor c = db.getRecord21(expnm.getText().toString());
				expnm.setText("");
				/*
			write code for calculate
			call calculate function from here which will return  the 
			2-d array
				 */
				/*	for(int j = 0; j < no_member; j++)
        		Toast.makeText(getBaseContext(), "FINAL MATRIX out function before" +membername[j]+ "\t" + paidbyamt[j]+"\t"+shareamt[j]+"\t"+no_member , Toast.LENGTH_LONG).show();
				 */
				Load(membername,paidbyamt,shareamt,no_member);

				/*for(int i=0;i<no_member;i++)
				for(int j = 0; j < no_member; j++)
					Toast.makeText(getBaseContext(), "FINAL MATRIX out function" +Fin[i][j]+cntf , Toast.LENGTH_LONG).show();
				 */


				for(int i=0;i<cnt;i++)
				{
					db.insertRecord3(getid, Integer.parseInt(c.getString(0)),Fin[i][0],Fin[i][1],Integer.parseInt(Fin[i][2]));
				}
				Intent returnIntent = new Intent();
				returnIntent.putExtra("id", getid);
				returnIntent.putExtra("Members", membername);
				returnIntent.putExtra("no", no_member);
				//	returnIntent.putExtra("count", j+1);
				//j++;
				//		Toast.makeText(getBaseContext(), "in here"+getid, Toast.LENGTH_LONG).show();
				setResult(RESULT_OK,returnIntent);
				//		Toast.makeText(getBaseContext(), "after set", Toast.LENGTH_LONG).show();
				finish();
			}
			else
			{
				Toast.makeText(getBaseContext(), "Enter Expense Name!!!", Toast.LENGTH_SHORT).show();
			}
			return true;
		}
		case android.R.id.home:
			Intent homeIntent = new Intent(this, AddExp.class);
			//	homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(homeIntent);
		default:   
			return super.onOptionsItemSelected(item);
		}
	}

	public void Is_Valid_Person_Name(EditText edt, int s) throws NumberFormatException {
		switch (s) {
		case 1:
			if (edt.getText().toString().length() <= 0) {
				edt.setError("Accept Alphabets Only.");
				valid_name = null;
			} else if (!edt.getText().toString().matches("([a-zA-Z ]*+[0-9]*)*")) {
				edt.setError("Accept Alphabets Only.");
				valid_name = null;
			} else {
				valid_name = edt.getText().toString();
			}

			break;

		case 2 :
			if (edt.getText().toString().length() <= 0) {
				edt.setError("Accept Numbers Only.");
				valid_name = null;
			} else if (!edt.getText().toString().matches("([0-9]*+[.]*)*")) {
				edt.setError("Accept Numbers Only.");
				valid_name = null;
			} else {
				valid_name = edt.getText().toString();
			}
			break;
		default:
			break;
		}

	}
	private Boolean exit = false;
	@Override
	public void onBackPressed() {
		if (exit)
			System.exit(0);
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





	public void Load(String[] name,int[] payarray,int[] indsh,int person)
	{
		int sum=0;

		/*		name[0] = "NIRANJAN";
		name[1] = "PARAS";
		name[2] = "AMITA";
		name[3] = "AISH";
		name[4] = "VED";
		payarray[0]=0;
		payarray[1]=0;
		payarray[2]=10;
		payarray[3]=20;
		payarray[4]=10;*/


		for(i=0;i<person;i++)
			sum+=payarray[i];
		if(flag != 2)
		{

			for(i=0;i<person;i++)
			{
				indsh[i]=sum/person;
			}
		}
		/*for(int j = 0; j < no_member; j++)
			Toast.makeText(getBaseContext(), "FINAL MATRIX in function before" +name[j]+ "\t" + payarray[j]+"\t"+indsh[j]+"\t"+person , Toast.LENGTH_LONG).show();
		 */
		/*	else
		{
			indsh[0] = 5;
			indsh[1] = 5;
			indsh[2] = 10;
			indsh[3] = 5;
			indsh[4] = 15;

		}*/
		//cal = 40;
		for(int i=0;i<person;i++)
		{
			arr1[i]=0;
			giving[i]=-1;
			take[i]=-1;
		}
		//if(sum == cal)
		//{
		for(int i=0;i<person;i++)
		{
			arr1[i] = payarray[i]-indsh[i];
		}
		for(int i=0;i<person;i++)
		{
			if(arr1[i]<0)
			{
				arr1[i] = -1*arr1[i];
				giving[j] = i;
				j++;
			}
			else if(arr1[i]>0)
			{
				take[k]=i;
				k++;
			}
		}// end of for 1
		for(i=0,j=0,k=0,l=0;i<person;i++,l++)
		{
			if((giving[j]>=0)&&(arr1[giving[j]]>=arr1[take[k]]))
			{
				h= arr1[take[k]];
				arr1[giving[j]]=arr1[giving[j]]-arr1[take[k]];
				arr1[take[k]]=0;

				//		System.out.println(name[giving[j]]+ " gives " + name[take[k]]+ " : "+h);
				/*	Toast.makeText(this, name[giving[j]]+ " gives " + name[take[k]]+ " : "+h,
							Toast.LENGTH_SHORT).show();*/
				Fin[l][0] = name[giving[j]];
				Fin[l][1] = name[take[k]];
				Fin[l][2] = h + "";
				take[k]=-1;
				k++;

				if(arr1[giving[j]]==0)
				{	
					giving[j]=-1;
					j++;
				}
				cnt++;
			}
			else if((giving[j]>=0)&&(arr1[giving[j]]<=arr1[take[k]]))
			{	
				tk = arr1[giving[j]];
				arr1[take[k]]=arr1[take[k]]-arr1[giving[j]];
				arr1[giving[j]]=0;
				//		System.out.println(name[giving[j]]+ " gives " + name[take[k]]+ " : "+tk);
				/*Toast.makeText(this, name[giving[j]]+ " gives " + name[take[k]]+ " : "+tk,
							Toast.LENGTH_SHORT).show();*/
				Fin[l][0] = name[giving[j]];
				Fin[l][1] = name[take[k]];
				Fin[l][2] = tk + "";


				giving[j]=-1;
				j++;
				if(arr1[take[k]]==0)
				{
					take[k]=-1;
					k++;
				}
				cnt++;
			}	
		}//end of for 2
		//	}// end of if
		/*for(i=0;i<cnt;i++)
			{for(k=0;k<3;k++)
				System.out.print(Fin[i][k] + "\t");
			System.out.println();}*/
		//cntf=cnt;
		/*	for(int i=0;i<cnt;i++)
				Toast.makeText(getBaseContext(), "FINAL MATRIX  " +Fin[i][0] + " gives "+ Fin[i][1] + " : "+ Fin[i][2] , Toast.LENGTH_LONG).show();*/


	}// end of load


}

