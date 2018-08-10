package ca.on.sl.comp208.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DPreacher on 2017-03-20.
 */

public class DBManager extends SQLiteOpenHelper {
    private final static String DBName = "demo.db";
    private final static int VERSION = 3;
    private Context context;

    public DBManager(Context context) {
        super(context, DBName, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DB","onCreate");
        String createString=
                "create TABLE " +
                        DBSchema.Musician.TABLE_NAME+" ( "+
                        DBSchema.Musician._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        DBSchema.Musician.FIRST+" TEXT, " +
                        DBSchema.Musician.LAST+" TEXT, " +
                        DBSchema.Musician.INSTRUMENT+" TEXT);";
        Log.i("DB","On Create: "+createString);
        db.execSQL(createString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE "+
                DBSchema.Musician.TABLE_NAME +";";
        db.execSQL(drop);
        onCreate(db);
    }
}
