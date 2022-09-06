/*package com.example.x_split0511;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
public class Database {
	public static final String KEY_Grp_Id = "Grp_Id";
	public static final String KEY_GRP_NAME = "Grp_Name";
	//public static final String KEY_EMAIL = "Email";
	private static final String DATABASE_NAME = "X_Split_DB";
	private static final String DATABASE_TABLE = "Grps_Record";
	private static final String DATABASE_TABLE1 = "Grp_Member_Record";
	private static final String DATABASE_TABLE2 = "Expenses";
	private static final String DATABASE_CREATE = 	"Create table if not exists Grps_Record(Grp_Id integer primary key autoincrement," + "Grp_Name VARCHAR);";
	private static final int DATABASE_VERSION = 1;
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	public Database(Context ctx)
	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try
			{
				
				db.execSQL(DATABASE_CREATE);
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public Database open() throws SQLException
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}
	public void close()
	{
		DBHelper.close();
	}
	public long insertRecord(String grname)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_GRP_NAME, grname);
		return db.insert(DATABASE_TABLE, null, initialValues);
		
	}
	//public Cursor getRecord(long grpid)
	{
			Cursor cr = db.query(true,DATABASE_TABLE, new String[]{KEY_Grp_Id,KEY_GRP_NAME},KEY_Grp_Id +  "=" + grpid, null, null, null, null, null);
			if(cr != null)
			{
				cr.movetofirst
			}
			return cr;
	  }
	
	public boolean deleteRecord(int grpid)
	{
		return db.delete(DATABASE_TABLE, KEY_Grp_Id + "=" + grpid, null) > 0;
	}
	public Cursor getAllRecord()
	{
		//Cursor cr = db.query(true, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
		//return db.rawQuery("Select * from" + DATABASE_TABLE, null);
		//return db.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)
		return db.query(DATABASE_TABLE, new String[]{KEY_Grp_Id,KEY_GRP_NAME},null, null, null, null, null);
	
	}
}
*/



package com.example.x_split0511;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.telephony.SmsManager;
import android.widget.Toast;
public class Database {
	public static final String KEY_Grp_Id = "Grp_Id";
	public static final String KEY_EXPENSE_ID = "Expense_Id";
	public static final String KEY_EXPENSE_NAME = "Expense_Name";
	public static final String KEY_GRP_NAME = "Grp_Name";
	//public static final String KEY_EMAIL = "Email";
	public static final String KEY_ID = "Id";
	public static final String KEY_CONTACT_NAME = "Contact_Name";
	public static final String KEY_CONTACT_NUMBER = "Contact_No";
	public static final String KEY_GRPID = "Grp_Id";
	public static final String KEY_GRPID3 = "Grp_Id";
	public static final String KEY_LASTID = "Id";
	public static final String KEY_EXPID = "Expense_Id";
	public static final String KEY_PER = "Person";
	public static final String KEY_PER1 = "Person1";
	public static final String KEY_AMT = "Amount";
	public static final String KEY_GID = "Grp_Id";
	public static final String KEY_EXID = "Expense_Id";
	public static final String KEY_NAME = "Name";
	public static final String KEY_AMT1 = "Amount";
	public static final String KEY_MEMBER_ID = "Member_Id";
	public static final String KEY_MEMBER_NAME = "Member_Name";
	private static final String DATABASE_NAME = "X_Split_DB";
	private static final String DATABASE_TABLE = "Grps_Record";
	private static final String DATABASE_TABLE1 = "Grp_Member_Record";
	private static final String DATABASE_TABLE2 = "Expenses_Record";
	private static final String DATABASE_TABLE3 = "Final_Record";
	//private static final String DATABASE_TABLE3 = "Grp_Member_Record";
	private static final String DATABASE_TABLE4 = "Contacts_Record";
	private static final String DATABASE_CREATE = 	"Create table if not exists Grps_Record(Grp_Id integer primary key autoincrement," + "Grp_Name VARCHAR);";
	private static final String DATABASE_CREATE5 = 	"Create table if not exists Finalbal_Record(Grp_Id integer primary key autoincrement," + "Grp_Name VARCHAR);";
	private static final String DATABASE_CREATE4 = 	"Create table if not exists Contacts_Record(Id integer primary key autoincrement," + "Contact_Name VARCHAR, Contact_No VARCHAR);";
	private static final String DATABASE_CREATE3 = 	"Create table if not exists Final_Record(Id integer primary key autoincrement," + "Grp_Id integer, Expense_Id integer, Person VARCHAR, Person1 VARCHAR, Amount integer);";
	private static final String DATABASE_CREATE2 = 	"Create table if not exists Expenses_Record(Expense_Id integer primary key autoincrement," + "Grp_Id integer, Expense_Name VARCHAR);";
	private static final String DATABASE_CREATE1 = 	"Create table if not exists Grp_Member_Record(Id1 integer primary key autoincrement," + "Grp_Id integer,Member_Id integer, Member_Name VARCHAR);";	
	private static final int DATABASE_VERSION = 1;
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	public Database(Context ctx)
	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try
			{
				
				db.execSQL(DATABASE_CREATE);
				db.execSQL(DATABASE_CREATE4);
				db.execSQL(DATABASE_CREATE2);
				db.execSQL(DATABASE_CREATE1);
				db.execSQL(DATABASE_CREATE3);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public Database open() throws SQLException
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}
	public void close()
	{
		DBHelper.close();
	}
	public long insertRecord(String grname)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_GRP_NAME, grname);
		return db.insert(DATABASE_TABLE, null, initialValues);
		
	}
	
	public long insertRecord4(String contact_name,String contact_no)
	{
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_CONTACT_NAME, contact_name);
		initialValues.put(KEY_CONTACT_NUMBER, contact_no);
		return db.insert(DATABASE_TABLE4, null, initialValues);
	}
	public long insertRecord1(int grpid, int memberid, String member_name)
	{
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_MEMBER_NAME, member_name);
		initialValues.put(KEY_GRPID, grpid);
		initialValues.put(KEY_MEMBER_ID, memberid);
		return db.insert(DATABASE_TABLE1, null, initialValues);
	}
	public long insertRecord2(int grpid, String expname)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_Grp_Id, grpid);
		initialValues.put(KEY_EXPENSE_NAME, expname);
		return db.insert(DATABASE_TABLE2, null, initialValues);
		
	}
	public long insertRecord3(int grpid, int expid, String per, String per1, int amt )
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_GRPID3, grpid);
		initialValues.put(KEY_EXPID, expid);
		initialValues.put(KEY_PER, per);
		initialValues.put(KEY_PER1, per1);
		initialValues.put(KEY_AMT, amt);
		return db.insert(DATABASE_TABLE3, null, initialValues);
	}
	public Cursor getRecord(String grpnm)
	{
			Cursor cr = db.query(true,DATABASE_TABLE, new String[]{KEY_Grp_Id},KEY_GRP_NAME +  "=?", new String[] { grpnm }, null, null, null, null);
			if(cr != null)
			{
				cr.moveToFirst();
			}
			return cr;
	  }
	public Cursor getRecord3(long expid)
	{
			Cursor cr = db.query(true,DATABASE_TABLE3, new String[]{KEY_PER, KEY_PER1, KEY_AMT},KEY_EXPID +  "=" + expid, null, null, null, null, null);
			if(cr != null)
			{
				cr.moveToFirst();
			}
			return cr;
	  }
	
	public Cursor getRecord2(long grpid)
	{
			Cursor cr = db.query(true,DATABASE_TABLE2, new String[]{KEY_EXPENSE_ID,KEY_EXPENSE_NAME},KEY_Grp_Id +  "=" + grpid, null, null, null, null, null);
			if(cr != null)
			{
				cr.moveToFirst();
			}
			return cr;
	 }
	public Cursor getRecord21(String expnm)
	{
		Cursor cr = db.query(true,DATABASE_TABLE2, new String[]{KEY_EXPENSE_ID},KEY_EXPENSE_NAME +  "=?", new String[] { expnm }, null, null, null, null);
			if(cr != null)
			{
				cr.moveToFirst();
			}
			return cr;
	  }
	
	public Cursor getRecord1(long grpid)
	{
			Cursor cr = db.query(true,DATABASE_TABLE1, new String[]{KEY_MEMBER_ID,KEY_MEMBER_NAME},KEY_GRPID +  "=" + grpid, null, null, null, null, null);
			if(cr != null)
			{
				cr.moveToFirst();
			}
			return cr;
	  }
	public Cursor getRecord31(long grpid)
	{
			Cursor cr = db.query(true,DATABASE_TABLE3, new String[]{KEY_PER,KEY_PER1,KEY_AMT},KEY_GRPID +  "=" + grpid, null, null, null, null, null);
			/*String abc = "SELECT * FROM Final_Record WHERE Grp_Id =" + grpid;
			Cursor cr = db.rawQuery(abc, null);*/
			if(cr != null)
			{
				cr.moveToFirst();
			}
			return cr;
	  }
	
	public boolean deleteRecord(int grpid)
	{
		return db.delete(DATABASE_TABLE, KEY_Grp_Id + "=" + grpid, null) > 0;
	}
	public boolean deleteRecord2(int expid)
	{
		return db.delete(DATABASE_TABLE2, KEY_EXPENSE_ID + "=" + expid, null) > 0;
	}
	public boolean deleteRecord3(int expid, String per, String per1)
	{
		return db.delete(DATABASE_TABLE3, KEY_EXPID + "=" + expid +" AND " +KEY_PER + "='" +per+"' AND " +KEY_PER1 + "='" +per1+"'", null) > 0;
	}
	public Cursor getAllRecord()
	{
		//Cursor cr = db.query(true, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
		//return db.rawQuery("Select * from" + DATABASE_TABLE, null);
		//return db.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)
		return db.query(DATABASE_TABLE, new String[]{KEY_Grp_Id,KEY_GRP_NAME},null, null, null, null, null);
	
	}
	public Cursor getAllRecord1()
	{
		//Cursor cr = db.query(true, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
		//return db.rawQuery("Select * from" + DATABASE_TABLE, null);
		//return db.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)
		return db.query(DATABASE_TABLE1, new String[]{KEY_GRPID,KEY_MEMBER_ID,KEY_MEMBER_NAME},null, null, null, null, null);
	
	}
	public Cursor getAllRecord2()
	{
		//Cursor cr = db.query(true, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
		//return db.rawQuery("Select * from" + DATABASE_TABLE, null);
		//return db.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)
		return db.query(DATABASE_TABLE2, new String[]{KEY_EXPENSE_ID,KEY_Grp_Id,KEY_EXPENSE_NAME},null, null, null, null, null);
	
	}
	public Cursor getAllRecord3()
	{
		//Cursor cr = db.query(true, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
		//return db.rawQuery("Select * from" + DATABASE_TABLE, null);
		//return db.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)
		return db.query(DATABASE_TABLE3, new String[]{KEY_GRPID3,KEY_EXPID,KEY_PER,KEY_PER1,KEY_AMT},null, null, null, null, null);
	
	}
	public Cursor getAllRecord4()
	{
		//Cursor cr = db.query(true, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
		//return db.rawQuery("Select * from" + DATABASE_TABLE, null);
		//return db.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)
		return db.query(DATABASE_TABLE4, new String[]{KEY_ID,KEY_CONTACT_NAME,KEY_CONTACT_NUMBER},null, null, null, null, null);
	
	}
}
