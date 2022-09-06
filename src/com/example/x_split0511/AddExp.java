package com.example.x_split0511;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
// Hello World

public class AddExp extends Activity {
	Database db = new Database(this);
	String grp;
	static int partgrpid;
	int Expenseid[] = new int[100];
	String Expensename[] = new String[100];
	String[] From1 = new String[200];
	String[] memname = new String[200];
	String[] To1 = new String[200];
	int[] Amtt1 = new int[200];
	int i = 0, per_cnt,ccnt1=0;
	String[][] Fin = new String[200][3];
	String[][] fin = new String[200][3];
	//BaseAdapter b;
	ListView lstexp;
	Button dn;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_exp);
		lstexp = (ListView)findViewById(R.id.lvw2);
		Intent e = getIntent();
		int temp = e.getIntExtra("id value", 0);
		partgrpid = temp;
		display();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		 if (requestCode == 3) {
		        if(resultCode == RESULT_OK){
		        
		 //       	Toast.makeText(getBaseContext(), "back", Toast.LENGTH_LONG).show();
		        	partgrpid = data.getIntExtra("id", 0);
		        	memname = data.getStringArrayExtra("Members");
		        	per_cnt = data.getIntExtra("no", 0);
		        	//displaying contents of expenses
		        	display();
		        }
		 }
	}
		 public void display()
		 {
			 	i = 0;
		  		db.open();
	    		Cursor c = db.getRecord2(partgrpid);
	    		if(c.moveToFirst())
	    		{
	    			do
	    			{
	    				Expenseid[i] = Integer.parseInt(c.getString(0));
	    				Expensename[i] = c.getString(1);
	    				i++;
	    			}
	    			while(c.moveToNext());
	    		}

	    		BaseAdapter b = new BaseAdapter() {
	    			
	    			@Override
	    			public View getView(int pos, View arg1, ViewGroup arg2) {
	    				// TODO Auto-generated method stub
	    				LayoutInflater lin = getLayoutInflater();
	    				View v = lin.inflate(R.layout.lstlay,null);
	    				TextView t = (TextView) v.findViewById(R.id.lst1);
	    				t.setText(Expensename[pos]);
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
	    		lstexp.setAdapter(b);
	    		lstexp.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int pos, long arg3) {
						// TODO Auto-generated method stub
						Intent ae = new Intent(getBaseContext(), Balance.class);
						ae.putExtra("expid", Expenseid[pos]);
						ae.putExtra("gid", partgrpid);
						startActivityForResult(ae, 7);
					}
				});
	        }
		 
	
//	@Override
	/*protected void onSaveInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(savedInstanceState);
		Toast.makeText(getBaseContext(), "in save", 1500).show();
		savedInstanceState.putInt("id",partgrpid);
	}*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_exp, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.doneexp:
			
			i = 0;
			db.open();
    		Cursor c = db.getAllRecord3();
    		if(c.moveToFirst())
    		{
    			do
    			{
    				if(Integer.parseInt(c.getString(0))==partgrpid)
    				{
    					From1[i] = c.getString(2);
    					To1[i] = c.getString(3);
    					Amtt1[i] = Integer.parseInt(c.getString(4));
    					i++;
    				}
    			}
    			while(c.moveToNext());
    		}
  //  		Toast.makeText(getBaseContext(), "cnt  " + per_cnt+"   ", 1500).show();
    		fin = FinalBal();
   // 		for(i=0;i<per_cnt;i++)
   // 			Toast.makeText(getBaseContext(), "FINAL MATRIX out " +fin[i][0] + " gives "+ fin[i][1] + " : "+ fin[i][2] , Toast.LENGTH_LONG).show();
    		/*for(int i=0;i<5;i++)
    		{
    			Toast.makeText(getBaseContext(), memname[i]+ " \t  "+From1[i] +" gives " + To1[i]+" : "+Amtt1[i], 1500).show();
    		}
    		*/
        	//Intent i = new Intent(this,CrtGrp.class);
            //startActivity(i);
            return true;
        case R.id.addexp:
        	Intent i1 = new Intent(this,CrtExp.class);
        	i1.putExtra("value", partgrpid);
        	//i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		//i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);                  
    		//i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(i1, 3);
            return true;
        case android.R.id.home:
	    	 Intent homeIntent = new Intent(this, AddGroup.class);
	            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(homeIntent);
         default:   
        	 return super.onOptionsItemSelected(item);
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
	
	public String[][] FinalBal()
	{
		int calcF = 0,calcT=0,i,j,k,l,h,tk;
		int[] FinFrom1 = new int[200];
		int[] FinTo1 = new int[200];
		
		int[] arr1 = new int[200];
		int[] giving = new int[200];
		int[] take = new int[200];
		for(j = 0;j<per_cnt;j++)
		{
			for(i=0;i<30;i++)
			{
				if(memname[j].equals(From1[i]))
				{
				//	Toast.makeText(getBaseContext(), "From " + From1[i]+" To "+To1[i]+" AMMT : "+Amtt1[i] , Toast.LENGTH_LONG).show();
					calcF += Amtt1[i];
				}
			}
			calcF = -1*calcF;
			FinFrom1[j] = calcF;
			calcF=0;
	//		Toast.makeText(getBaseContext(), "FinFrom "+FinFrom1[j] , Toast.LENGTH_LONG).show();
		}
		for(j = 0;j<per_cnt;j++)
		{
			for(i=0;i<30;i++)
			{
				
				if(memname[j].equals(To1[i]))
				{
				//	Toast.makeText(getBaseContext(), "From " + To1[i]+" To "+From1[i]+" AMMT : "+Amtt1[i] , Toast.LENGTH_LONG).show();
					calcT += Amtt1[i];
				}
			}
			FinTo1[j] = calcT;
			calcT=0;
//			Toast.makeText(getBaseContext(), "FinFrom "+FinTo1[j] , Toast.LENGTH_LONG).show();
		}
		for(j =0;j<per_cnt;j++)
		{
			arr1[j] = FinFrom1[j]+FinTo1[j];
		}
	
		for(i=0;i<per_cnt;i++)
		{
			giving[i]=-1;
			take[i]=-1;
		}
		
//		for(i=0;i<5;i++)
//			Toast.makeText(getBaseContext(), "FINAL MATRIX From To " +FinFrom1[i] + " gives "+ FinTo1[i] + " : "+ arr1[i] , Toast.LENGTH_LONG).show();
		
		for(i=0,j=0,k=0;i<per_cnt;i++)
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
		for(i=0,j=0,k=0,l=0;i<per_cnt;i++,l++)
		{
			if((giving[j]>=0)&&(arr1[giving[j]]>=arr1[take[k]]))
			{
				h= arr1[take[k]];
				arr1[giving[j]]=arr1[giving[j]]-arr1[take[k]];
				arr1[take[k]]=0;

				//		System.out.println(name[giving[j]]+ " gives " + name[take[k]]+ " : "+h);
				/*	Toast.makeText(this, name[giving[j]]+ " gives " + name[take[k]]+ " : "+h,
							Toast.LENGTH_SHORT).show();*/
				Fin[l][0] = memname[giving[j]];
				Fin[l][1] = memname[take[k]];
				Fin[l][2] = h + "";
				take[k]=-1;
				k++;

				if(arr1[giving[j]]==0)
				{	
					giving[j]=-1;
					j++;
				}
				ccnt1++;
			}
			else if((giving[j]>=0)&&(arr1[giving[j]]<=arr1[take[k]]))
			{	
				tk = arr1[giving[j]];
				arr1[take[k]]=arr1[take[k]]-arr1[giving[j]];
				arr1[giving[j]]=0;
				//		System.out.println(name[giving[j]]+ " gives " + name[take[k]]+ " : "+tk);
				/*Toast.makeText(this, name[giving[j]]+ " gives " + name[take[k]]+ " : "+tk,
							Toast.LENGTH_SHORT).show();*/
				Fin[l][0] = memname[giving[j]];
				Fin[l][1] = memname[take[k]];
				Fin[l][2] = tk + "";


				giving[j]=-1;
				j++;
				if(arr1[take[k]]==0)
				{
					take[k]=-1;
					k++;
				}
				ccnt1++;
			}	
		}//end of for 2
		//	}// end of if
		/*for(i=0;i<cnt;i++)
			{for(k=0;k<3;k++)
				System.out.print(Fin[i][k] + "\t");
			System.out.println();}*/
		//cntf=cnt;
		

	/*	for(i=0;i<2;i++)
			Toast.makeText(getBaseContext(), "FINAL MATRIX  " +Fin[i][0] + " gives "+ Fin[i][1] + " : "+ Fin[i][2] , Toast.LENGTH_LONG).show();*/
		
		return Fin;
		
		
	}

}