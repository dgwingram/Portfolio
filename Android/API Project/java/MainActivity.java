package ca.on.sl.comp208.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by DGWIngram on 2017-03-20.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBManager dbManager= new DBManager(this);
        SQLiteDatabase db = dbManager.getWritableDatabase();

        populate(db);
        try{
            String[]dbColumns = {
                    DBSchema.Musician._ID,
                    DBSchema.Musician.FIRST,
                    DBSchema.Musician.LAST,
                    DBSchema.Musician.INSTRUMENT };
            // Like select null from ...
            Cursor cursor = db.query(DBSchema.Musician.TABLE_NAME,
                    dbColumns,null,null,null,null,null);
            //needs to be in the same order as the dbColumns to align Columns
            int[] layoutColumns = {R.id.nameId,R.id.firstname,R.id.lastname,R.id.instrument};

            ListAdapter adapter;
            adapter = new SimpleCursorAdapter(this, R.layout.item_layout,cursor,dbColumns,layoutColumns,0);
            ListView lv = (ListView) findViewById(R.id.namelist);
            lv.setAdapter(adapter);
        } catch (Exception e){
            Log.i("DBinit","Error: "+e.getMessage());
        }
    }

    public void populate (SQLiteDatabase db){
        db.execSQL("DELETE FROM " +DBSchema.Musician.TABLE_NAME);
        String insertStmt = " INSERT INTO "+DBSchema.Musician.TABLE_NAME +" VALUES " +
                "(NULL,'Miles','Davis','drums'), "+
                "(NULL,'Big','Drum','trumpet'), "+
                "(NULL,'Miles','Harry','horn'), "+
                "(NULL,'Ford','Davis','piano'), "+
                "(NULL,'Gill','Davis','saxophone'), "+
                "(NULL,'Willy','Nilly','flute');";
        Log.i("populate",insertStmt);
        db.execSQL(insertStmt);
    }
}
