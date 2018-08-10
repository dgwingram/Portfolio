package ca.on.sl.comp208.assignment3;

import android.net.Uri;

/**
 * Created by DGWIngram on 2017-04-10.
 */

public final class DataContract {

    public static final Uri CONTENT_URI = Uri.parse("http://labs.bible.org/api/?");

    public static final String BOOKNAME = "";
    public static final int CHAPTER = 1;
    public static final int VERSE = 1;
    public static final String TEXT = "";
    public static final String TITLE = "";

    public static final int CONDITIONS = 5;

    public static final String [] columnNames = {"bookname","chapter","verse","text","title","Conditions"};

}
