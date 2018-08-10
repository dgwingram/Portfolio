package ca.on.sl.comp208.assign2;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by DGWIngram on 2017-01-23.
 */ 

public class MainActivity extends AppCompatActivity {

    private DrawView view;
    private Canvas canvas;
    boolean cells[][][] = new boolean[2][16][16];
    //private Bitmap bmp;
    private SurfaceHolder holder;
    int width,height,x0,y0, pixelWidth, pixelHeight;
    boolean init;
    Generation lfBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (DrawView)findViewById(R.id.canvas);
        holder = view.getHolder();
        init = true; width =0; height =0;

        view.post(new Runnable(){

            @Override
            public void run() {
                Log.i("MainAct: ","New Game");
                view.newGame();
                lfBoard=view.cells;
                    /*
                //view.newGame(view.getWidth(),view.getHeight());
                Paint cellBrush = new Paint();
                //getResources().getColor(R.color.colorCells,)
                //ContextCompat.getColor(R.color.colorCells);

                cellBrush.setARGB(100,255,69,0);
                cellBrush.setStrokeWidth(3);
                Paint brush2 = new Paint();
                brush2.setARGB(100,255,255,255);

                lfBoard = new Generation(holder,cellBrush,brush2,width,height);
                Random rand = new Random(System.currentTimeMillis());
                */
            }
        });
    }/*
    public void init2(){
        lfBoard = new LifeBoard();

        lfBoard.drawNextGen(holder,
                width,
                height,
                pixelWidth,
                pixelHeight
        );
    }*/
    public void init(){

        canvas = holder.lockCanvas();
/*
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);*/

        Paint whiteBrush = new Paint();
        Paint yellowBrush = new Paint();
        Paint usedBrush = new Paint();
        whiteBrush.setColor(Color.WHITE);
        yellowBrush.setColor(Color.YELLOW);

        //int screenWidth, screenHeight;
        /*screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight =  Resources.getSystem().getDisplayMetrics().heightPixels;
        */

        /*
        screenWidth = size.x;
        screenHeight = size.y;*/



        int x,y;
        pixelWidth = width /16;
        pixelHeight = height/16;
        for(int v = 0; v < 16; v++){
            if((v%2)==0)
                usedBrush = whiteBrush;
            else
                usedBrush=yellowBrush;
            x=v*pixelWidth;
            for(int h=0;h<16;h++) {
                if(((h+v)%2)==0)
                    usedBrush = whiteBrush;
                else
                    usedBrush=yellowBrush;
                y = h*pixelHeight;
                canvas.drawRect(x, y,x + width,y+ height, usedBrush);
            }
        }

        holder.unlockCanvasAndPost(canvas);
    }
    public void draw(View view){
        canvas = holder.lockCanvas();
        Paint blueBrush = new Paint();
        blueBrush.setColor(Color.BLUE);
        Bitmap bp;
        bp = Bitmap.createBitmap(Math.round(width),Math.round(height), Config.ARGB_8888);
        canvas.drawBitmap(bp,width,height,blueBrush);

        if(x0<width)
            x0 += pixelWidth;
        else {
            x0 = 0;
            y0 += pixelHeight;
        }
        canvas.drawRect(x0,y0,x0+pixelWidth,y0+pixelHeight,blueBrush);

        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Style");
        menu.add("Colour");
        menu.add("width");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        view.nextGen();
        Log.i("Menu","Item Selected");
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item); //returns the value of the base constructor
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Canvas canvas = view.getHolder().lockCanvas();
        //Bitmap bmp Bitmap.createBitmap()

        //  view.nextGen();
        lfBoard.drawNextGen(view.getHolder());


        return super.onTouchEvent(event); //returns the value of the base constructor

    }
}
