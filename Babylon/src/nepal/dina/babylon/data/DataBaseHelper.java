package nepal.dina.babylon.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import nepal.dina.babylon.WordsMapper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	// The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/nepal.dina.babylon/databases/";

	private static String DB_NAME = "d.db";

	private SQLiteDatabase myDataBase;

	private final Context myContext;
	
	
	
	public void saveWord(String word, String note, String lng, String lvl){
		
		//openDataBase();
		//myDataBase.beginTransaction();
		
		// prvo provjeriti da li postoji ta grupa
		Cursor c = myDataBase.query("mygroup", new String[]{"_id", }, "lvl = '" + lvl + "' and lng = '" + lng + "'",
				 null, null, null, null, "100");
		
		String id = null;
		if (c != null ) {
		    if  (c.moveToFirst()) {
		        do {
		            id = c.getString(c.getColumnIndex("_id"));		            
		        }while (c.moveToNext());
		    }
		}
		c.close();
		
		// ako ne postoji, stvoriti
		long success = -1;
		if(id == null){
			ContentValues cv = new ContentValues();
			cv.put("lvl", lvl); // TODOs
			cv.put("lng", lng);
			success = myDataBase.insert("mygroup", null, cv);
			
			if(success == -1)return;
		}
		
		//myDataBase.endTransaction();
		//closeDataBase();
		//openDataBase();
		//myDataBase.beginTransaction();
		
		// dodati zapis u myword
//		ContentValues cv = new ContentValues();
//		cv.put("word", word);
//		cv.put("mygroup", String.valueOf(success));
//		//cv.put("note", note);
//		success = myDataBase.insert("myword", null, cv);
		
		myDataBase.execSQL("INSERT INTO myword (word, mygroup, note) " +
	    "VALUES ('" + word + "', " + new Long(success).intValue() + ", '" + note + "')");
		
		
		//myDataBase.endTransaction();
		//closeDataBase();
		
	}
	
	
	public ArrayList<Pair<String, String>> getMyWordss(String id){
		
		//openDataBase();

		Cursor c = myDataBase.query("myword", new String[]{"word", "note"}, "mygroup = " + id, null, null, null, null, "100");
		ArrayList<Pair<String, String>> ret = new ArrayList<Pair<String, String>>();
		
		
		if (c != null ) {
		    if  (c.moveToFirst()) {
		        do {
		            String word = c.getString(c.getColumnIndex("word"));
		            String note = c.getString(c.getColumnIndex("note"));
		            
		            Pair<String, String> p = new Pair<String, String>();
		            p.first = word;
		            p.second = note;
		            
		            ret.add(p);
		            
		        }while (c.moveToNext());
		    }
		}
		c.close();
		
		//closeDataBase();
		
		return ret;
	}
	
	public HashSet<MyGroup> getMyGroups(){
		//openDataBase();
		
		String selectionQuery = "select * from mygroup";
		int i = myDataBase.rawQuery(selectionQuery, null).getCount();
		 

		Cursor c = myDataBase.query("mygroup", new String[]{"_id", "lng", "lvl"}, "", null, null, null, null, "100");
		HashSet<MyGroup> ret = new HashSet<MyGroup>();
		
		
		if (c != null ) {
		    if  (c.moveToFirst()) {
		        do {
		            String id = c.getString(c.getColumnIndex("_id"));
		            String lng = c.getString(c.getColumnIndex("lng"));
		            String lvl = c.getString(c.getColumnIndex("lvl"));
		             
		            MyGroup mg = new MyGroup(id, lng, lvl);
		            
		            ArrayList<Pair<String, String>> ws = getMyWordss(id);
		            if(ws.size()>0){
		            	mg.setWords(ws);
			            ret.add(mg);
		            }
		            
		            
		        }while (c.moveToNext());
		    }
		}
		c.close();
		
		//closeDataBase();
		
		return ret;
	}
	
	public ArrayList<SmallQuestion> getQuestions(String lng, String level, String num){
		
		//openDataBase();

		Cursor c = myDataBase.query(lng, new String[]{"phrase1","phrase2"}, "level = '" + level + "'", null, null, null, null, num);
		ArrayList<SmallQuestion> ret = new ArrayList<SmallQuestion>();
		
		
		if (c != null ) {
		    if  (c.moveToFirst()) {
		        do {
		            String phrase1 = c.getString(c.getColumnIndex("phrase1"));
		            String phrase2 = c.getString(c.getColumnIndex("phrase2"));
		            
		            ret.add(new SmallQuestion(phrase1, phrase2));
		            
		        }while (c.moveToNext());
		    }
		}
		c.close();
		
		//closeDataBase();
		return ret;
	}

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	public DataBaseHelper(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}
	
	public SQLiteDatabase getMyDataBase() {
		return myDataBase;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist =  checkDataBase(); // false

		if (dbExist) {
			// do nothing - database already exist
		} else {

			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			
			this.getWritableDatabase().close();

			try {

				copyDataBase();
				

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READWRITE);
			
			

		} catch (SQLiteException e) {

			// database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[4096];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);
	}
	
	
	public void closeDataBase(){

		if(myDataBase != null) myDataBase.close();
	}

	
	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	// Add your public helper methods to access and get content from the
	// database.
	// You could return cursors by doing "return myDataBase.query(....)" so it'd
	// be easy
	// to you to create adapters for your views.

}
