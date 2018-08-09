package ca.on.sl.comp208.myapplication;

import android.provider.BaseColumns;

/**
 * Created by DGWIngram on 2017-03-20.
 */

public class DBSchema {
    public static final class Musician implements BaseColumns {
        public static final String TABLE_NAME = "Musician";
        public static final String FIRST = "firstname";
        public static final String LAST = "lastname";
        public static final String INSTRUMENT = "instrument";
    }

}
