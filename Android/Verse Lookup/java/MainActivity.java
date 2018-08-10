package ca.on.sl.comp208.assignment3;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by DGWIngram on 2017-01-23.
 */

public class MainActivity extends AppCompatActivity {
    //Global Views
    TextView v;
    Spinner book;
    NumberPicker chp, verse, range;
    private boolean newRequest;
    //Initialize Class Variables
    BookOutline selectedBook;
    Chapter selectedChapter;
    ArrayList<BookOutline> netVersion;

    //Initialize REST variables
    private final String URL = "http://labs.bible.org/api/?";
    private String passage, formatting;
    HttpConnectionTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Views used Globally
        v = (TextView)findViewById(R.id.output);
        book = (Spinner)findViewById(R.id.spnBooks);
        chp = (NumberPicker)findViewById(R.id.nbrChp);
        verse = (NumberPicker) findViewById(R.id.nbrVerse);
        range = (NumberPicker) findViewById(R.id.nbrRange);

        //Allow NumberPickers to wrap
        chp.setWrapSelectorWheel(true);
        verse.setWrapSelectorWheel(true);
        range.setWrapSelectorWheel(true);

        //initialize REST variables
        passage="random";
        formatting="plain";

        //Get Verse of the Day
        task = new HttpConnectionTask();
        task.execute(URL+"passage=votd&formatting="+formatting+"&type=json");

        //Initialize chapters and vese for
        initBible();


        /*
        *                           Initialize Listeners
        * */
        book.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choosenBook = book.getItemAtPosition(position).toString();
                Log.d("spinner","Selected: "+choosenBook);
                for(BookOutline b:netVersion){
                    Log.d("spinner","Book Compared "+selectedBook.getBookName()+"=="+b.getBookName()+"? ");

                    if(choosenBook.toString().equals(b.getBookName().toString())){
                        Log.d("spinner","Current: "+b.getBookName());
                        selectedBook=b;

                        chp.setMinValue(1);
                        chp.setMaxValue(b.getNumChp());
                        chp.setValue(1);

                        verse.setMinValue(1);
                        verse.setMaxValue(b.getChp().get(0).getNumberOfVerses());
                        verse.setValue(1);


                        range.setMinValue(1);
                        range.setMaxValue(b.getChp().get(0).getNumberOfVerses());
                        range.setValue(1);
                        range.setEnabled(false);
                    }
                }

                Log.d("spinner","Book: " + book.getItemAtPosition(position).toString()+", ID:"+id+", pos"+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        chp.setOnValueChangedListener(new NumberPicker.OnValueChangeListener(){

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Store Current verse
                selectedChapter = selectedBook.getChp().get(newVal-1);
                int maxVerses = selectedChapter.getNumberOfVerses();

                //Update Max Value for Verse Number Picker
                verse.setValue(1);
                verse.setMaxValue(maxVerses);
                if(verse.getValue() >maxVerses)
                    verse.setValue(maxVerses);

                //Update Max Value for Range Number Picker
                range.setMaxValue(maxVerses);
                range.setEnabled(false);
                if(range.getValue() > maxVerses)
                    range.setValue(maxVerses);

                Log.d("ChapterSpinner","New Value: "+newVal+" Chapter: "+selectedChapter.toString());
            }
        });

        verse.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                range.setEnabled(true);
                range.setMinValue(verse.getValue());
                range.setValue(verse.getValue());
            }
        });

    }

    private void initBible(){
        netVersion = new ArrayList<>();
        newRequest = true;
        //Initialize book of Daniel
        BookOutline book = new BookOutline("Daniel",12);
        Chapter nextChapter = new Chapter(1,21);// Chp1
                book.addChp(nextChapter);
                nextChapter = new Chapter(2,49);// Chp2
                book.addChp(nextChapter);
                nextChapter = new Chapter(3,30);// Chp3
                book.addChp(nextChapter);
                nextChapter = new Chapter(4,47);// Chp4
                book.addChp(nextChapter);
                nextChapter = new Chapter(5,31);// Chp5
                book.addChp(nextChapter);
                nextChapter = new Chapter(6,28);// Chp6
                book.addChp(nextChapter);
                nextChapter = new Chapter(7,28);// Chp7
                book.addChp(nextChapter);
                nextChapter = new Chapter(8,27);// Chp8
                book.addChp(nextChapter);
                nextChapter = new Chapter(9,49);// Chp9
                book.addChp(nextChapter);
                nextChapter = new Chapter(10,49);// Chp10
                book.addChp(nextChapter);
                nextChapter = new Chapter(11,21);// Chp11
                book.addChp(nextChapter);
                nextChapter = new Chapter(12,49);// Chp12
                book.addChp(nextChapter);
            netVersion.add(book);
            selectedBook = book;

        //Initialize book of Mark
        book = new BookOutline("Mark",16);
                nextChapter = new Chapter(1,45);// Chp1
                book.addChp(nextChapter);
                nextChapter = new Chapter(2,28);// Chp2
                book.addChp(nextChapter);
                nextChapter = new Chapter(3,35);// Chp3
                book.addChp(nextChapter);
                nextChapter = new Chapter(4,41);// Chp4
                book.addChp(nextChapter);
                nextChapter = new Chapter(5,43);// Chp5
                book.addChp(nextChapter);
                nextChapter = new Chapter(6,56);// Chp6
                book.addChp(nextChapter);
                nextChapter = new Chapter(7,37);// Chp7
                book.addChp(nextChapter);
                nextChapter = new Chapter(8,38);// Chp8
                book.addChp(nextChapter);
                nextChapter = new Chapter(9,50);// Chp9
                book.addChp(nextChapter);
                nextChapter = new Chapter(10,52);// Chp10
                book.addChp(nextChapter);
                nextChapter = new Chapter(11,33);// Chp11
                book.addChp(nextChapter);
                nextChapter = new Chapter(12,44);// Chp12
                book.addChp(nextChapter);
                nextChapter = new Chapter(13,37);// Chp13
                book.addChp(nextChapter);
                nextChapter = new Chapter(14,72);// Chp14
                book.addChp(nextChapter);
                nextChapter = new Chapter(15,47);// Chp15
                book.addChp(nextChapter);
                nextChapter = new Chapter(16,20);// Chp16
                book.addChp(nextChapter);
            netVersion.add(book);

        //Initialize book of Romans
        book = new BookOutline("Romans",16);
                nextChapter = new Chapter(1,32);// Chp1
                book.addChp(nextChapter);
                nextChapter = new Chapter(2,29);// Chp2
                book.addChp(nextChapter);
                nextChapter = new Chapter(3,31);// Chp3
                book.addChp(nextChapter);
                nextChapter = new Chapter(4,25);// Chp4
                book.addChp(nextChapter);
                nextChapter = new Chapter(5,21);// Chp5
                book.addChp(nextChapter);
                nextChapter = new Chapter(6,23);// Chp6
                book.addChp(nextChapter);
                nextChapter = new Chapter(7,25);// Chp7
                book.addChp(nextChapter);
                nextChapter = new Chapter(8,39);// Chp8
                book.addChp(nextChapter);
                nextChapter = new Chapter(9,33);// Chp9
                book.addChp(nextChapter);
                nextChapter = new Chapter(10,21);// Chp10
                book.addChp(nextChapter);
                nextChapter = new Chapter(11,36);// Chp11
                book.addChp(nextChapter);
                nextChapter = new Chapter(12,21);// Chp12
                book.addChp(nextChapter);
                nextChapter = new Chapter(13,14);// Chp13
                book.addChp(nextChapter);
                nextChapter = new Chapter(14,23);// Chp14
                book.addChp(nextChapter);
                nextChapter = new Chapter(15,33);// Chp15
                book.addChp(nextChapter);
                nextChapter = new Chapter(16,27);// Chp16
                book.addChp(nextChapter);
            netVersion.add(book);

        //Initialize book of 1 Peter
        book = new BookOutline("1 Peter",4);
                nextChapter = new Chapter(1,25);// Chp1
                book.addChp(nextChapter);
                nextChapter = new Chapter(2,25);// Chp2
                book.addChp(nextChapter);
                nextChapter = new Chapter(3,22);// Chp3
                book.addChp(nextChapter);
                nextChapter = new Chapter(4,19);// Chp4
                book.addChp(nextChapter);
                nextChapter = new Chapter(5,14);// Chp5
                book.addChp(nextChapter);
            netVersion.add(book);

        for(BookOutline b:netVersion){
            Log.d("spinner",b.toString());
        }
        //Log.d("spinner",netVersion.toString());
    }

    public void submitRequest(View view){
        HttpConnectionTask task = new HttpConnectionTask();
        if(newRequest)
            passage=currentVerse();
        task.execute(URL+"passage="+passage+"&formatting="+formatting+"&type=json");
    }
    public void addVerse(View view){

        String verses = currentVerse();
        passage+=";";
        if(!newRequest) {
            passage += ";" + verses;
            v.setText(v.getText()+"\n"+verses);
        }
        else {
            newRequest=false;
            passage = verses;
            v.setText(verses);
        }
    }
    public void randomVerse(View view){
        HttpConnectionTask task = new HttpConnectionTask();
        task.execute(URL+"passage=random&formatting="+formatting+"&type=json");
    }

    private String currentVerse(){
        String returnString;
        returnString = book.getSelectedItem().toString()
                +"%20"+chp.getValue()
                +":"+verse.getValue()
                +(verse.getValue()==range.getValue()?
                    "":
                    "-"+range.getValue()
                 );
        Log.d("verse",returnString);
        return returnString;
    }

    private class HttpConnectionTask extends AsyncTask<String, String, String> {
        String text;
        int i;
        Gson gson;
        VerseData[] verses;
        @Override
        protected void onPreExecute() {
            newRequest=true;
            gson = new Gson();
            i=0;
            v.setText("Retrieving Verse\n"+v.getText());
            text = "";
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            URL url = null;
            HttpURLConnection urlConnection=null;
            try {
                url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.connect();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                while((line = in.readLine())!=null){
                    i++;

                    text += line;
                }

                verses = gson.fromJson(text,VerseData[].class);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(urlConnection!=null)
                    urlConnection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            /*String title="empty";
            String passage="empty";*/
            String verseText =(verses[0].getTitle()==null?"":verses[0].getTitle()+"\n")+verses[0].getBookname()+" "+verses[0].getChapter()+":"+verses[0].getVerse()+"\n"+verses[0].getText();
            //StringEscapeUtils.unescapeHtml(yourString);
            // TODO: 2017-04-14 Figure out how to decode html code and put it into normal characters 
/*
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Spanned sp =Html.fromHtml(verses[0].getTitle(), Html.FROM_HTML_MODE_LEGACY);
                title = sp.toString();
                sp=Html.fromHtml(verses[0].getText(), Html.FROM_HTML_MODE_LEGACY);
                passage = sp.toString();
            }else{
                Spanned sp = Html.fromHtml(verses[0].getTitle());
                title =sp.toString();
                sp  = Html.fromHtml(verses[0].getText());
                passage =sp.toString();
            }*/
            v.setText(verseText);
            Log.i("ReadingData","Looped "+i+" time(s)");
            super.onPostExecute(s);
        }
    }
}
