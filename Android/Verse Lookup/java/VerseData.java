package ca.on.sl.comp208.assignment3;

/**
 * Created by DGWIngram on 2017-04-10.
 */

public class VerseData {
    private String bookname;
    private int chapter;
    private int verse;
    private String text;
    private String title;

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getVerse() {
        return verse;
    }

    public void setVerse(int verse) {
        this.verse = verse;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return " "+bookname+" "+this.chapter+":"+this.verse+": "+this.text+" "+title+ " " +super.toString();
    }
}
