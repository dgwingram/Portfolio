package ca.on.sl.comp208.assign2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

/**
 * Created by dingram20 on 1/30/2017.
 */
public class DrawView extends SurfaceView {
    float x0,y0,x1,y1;
    Generation cells;
    Paint cellBrush, backgroundBrush;
    int width,height,cellWidth,cellHeight;


    public DrawView(Context context, AttributeSet attrs) {

        super(context, attrs);
        setWillNotDraw(false);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

            setWillNotDraw(false);
        // onDraw needs to Draw between line segments
        //comes with canvas to draw items.
        //may want to keep the set of line segments to keep track of lines
    }

    public void newGame(){

        //setWillNotDraw(false);*

        this.height=height;
        this.width=width;

        cellBrush = new Paint();
        cellBrush.setColor(Color.BLUE);
        cellBrush.setStrokeWidth(3);;

        backgroundBrush = new Paint();
        backgroundBrush.setColor(Color.YELLOW);
        backgroundBrush.setStrokeWidth(3);


        this.cells = new Generation(this.getHolder(),cellBrush,backgroundBrush,this.getWidth(),this.getHeight());
        //invalidate();
    }

    public void nextGen(){
        //setWillNotDraw(false);
        this.cells.drawNextGen(this.getHolder());
        //invalidate();
    }
}
