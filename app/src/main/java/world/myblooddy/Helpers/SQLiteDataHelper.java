package world.myblooddy.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jacob Samro on 06-Apr-17.
 */

public class SQLiteDataHelper extends SQLiteOpenHelper {

    Context context;

    public SQLiteDataHelper(Context context){
        super(context, "database", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    sqLiteDatabase.execSQL("CREATE TABLE contacts (id TEXT PRIMARY KEY, name TEXT, group VARCHAR(5)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



    public boolean updateBloodGroup(String id, String group){
        return true;
    }
}
