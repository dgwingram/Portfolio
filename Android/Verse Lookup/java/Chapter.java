package ca.on.sl.comp208.assignment3;

/**
 * Created by DGWIngram on 2017-04-12.
 */

public class Chapter {
    private int verses;
    private int chapterNum;

    public Chapter(int chapterNum, int verses) {
        this.chapterNum = chapterNum;

        this.verses = verses;
    }

    public int getNumberOfVerses() {
        return verses;
    }

    public int getChapterNum() {
        return chapterNum;
    }

    @Override
    public String toString() {
        return "\n\t{\"Chapter\":\""+this.chapterNum+"\",\n\t\"Number of Verse\":\""+this.verses+"\"";
    }
}
