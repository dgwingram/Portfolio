package ca.on.sl.comp208.assign2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.Random;

/**
 * Created by DPreacher on 2017-03-05.
 */

public class Generation {
    /*  Rules:
        1.  Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
        2.  Any live cell with two or three live neighbours lives on to the next generation.
        3.  Any live cell with more than three live neighbours dies, as if by overpopulation.
        4.  Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction
    */
    private Boolean cellAlive[][][];
    private int screenWidth, screenHeight;
    private int cellWidth, cellHeight;
    Paint cellBrush, gameBoardBrush;
    private int numRow,numCol;
    /**
     *
     * @param holder SurfaceHolder from custom view
     * @param cellBrush Brush to be used for cellAlive
     * @param gameBoardBrush Brush to be used for the board
     * @param screenWidth Available width for the screen
     * @param screenHeight Available height for the screen
     */
    public Generation(SurfaceHolder holder, Paint cellBrush, Paint gameBoardBrush, int screenWidth, int screenHeight) {
        numCol = numRow = 16;
        cellAlive = new Boolean[2][numRow][numCol];
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.cellBrush = cellBrush;
        this.gameBoardBrush = gameBoardBrush;

        this.cellWidth = this.screenWidth/numRow;
        this.cellHeight =this.screenHeight/numCol;

        int cellSpacing=3;

        Random rndm = new Random(System.currentTimeMillis());

        for(int row = 0;row<numRow;row++){
            for(int col=0;col<numCol;col++) {
                int rndNum = rndm.nextInt(100);
                if(rndNum<25) {
                    cellAlive[0][row][col]=true;
                }else {
                    cellAlive[0][row][col] = false;
                    //cellSpacing=3;
                }
            }
        }
        updateAllCells(holder);
    }
    public void drawNextGen(SurfaceHolder holder){
        Canvas canvas = holder.lockCanvas();//Lock Canvas to prevent other use
        //New Generation Started
        Bitmap bmp = Bitmap.createBitmap(screenWidth,screenHeight, Bitmap.Config.ARGB_8888);//Create screen
        canvas.drawBitmap(bmp,screenWidth,screenHeight,cellBrush);
        /*
        *   Check Cells for Life or Death
        * */

        int nieghbours;
        for(int y=0;y<numRow;y++){
            for(int x = 0; x<numCol;x++){
                nieghbours = 0;
                //Check number Cells Adjacent
                for(int j = -1; j <2; j++) {
                    int cy = wrapValue(y+j,numRow);
                    //Count adjacent cellAlive

                    for (int i = -1; i < 2; i++) {
                        if(i==0&&j==0)
                            continue;
                        int cx=wrapValue(x+i,numCol);
                        // Check if Adjacent Cell has Life
                        if (cellAlive[0][cy][cx])
                            nieghbours++;//Adjacent Cell has life
                    }
                }
                cellAlive[1][y][x] = cellState(cellAlive[0][y][x],nieghbours);//Check if cell lives or dies
           }
        }
        /*
        *   Update
        * */
        for(int row=0;row<numRow;row++){
            float cellX = row*cellWidth;
            for(int col=0;col<numCol;col++){
                float cellY = col*cellHeight;
                canvas.drawRect(cellX, cellY,
                        cellX + cellWidth, cellY + cellHeight,
                        chooseBrsuh(cellAlive[1][row][col]));
      /*
                if(cellAlive[1][row][col])
                    canvas.drawRect(cellX, cellY,
                            cellX + cellWidth, cellY + cellHeight,
                            cellBrush);
                else
                    canvas.drawRect(cellX, cellY,
                            cellX + cellWidth, cellY + cellHeight,
                            gameBoardBrush);*/
                cellAlive [0][row][col]=cellAlive[1][row][col];
            }
        }
        holder.unlockCanvasAndPost(canvas);
    }
    public void updateAllCells(SurfaceHolder holder){

        Canvas canvas = holder.lockCanvas();
        Bitmap bmp = Bitmap.createBitmap(Math.round(screenWidth),Math.round(screenHeight), Bitmap.Config.ARGB_8888);
        canvas.drawBitmap(bmp,screenWidth,screenHeight, gameBoardBrush);
        canvas.drawRect(0f,0f,screenWidth,screenHeight, gameBoardBrush);
        for(int row = 0; row < numRow;row++){
            float cellX = row * cellWidth;
            for(int col = 0; col<numCol;col++){
                float cellY = col * cellHeight;
                canvas.drawRect( cellX,cellY,cellX+cellWidth,cellY+cellHeight, chooseBrsuh(cellAlive[0][row][col]));
            }
        }
        holder.unlockCanvasAndPost(canvas);
    }
    private Paint chooseBrsuh(Boolean cellLives){
        if(cellLives)
            return cellBrush;
        else
            return gameBoardBrush;
    }
    /*  Rules:
        1.  Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
        2.  Any live cell with two or three live neighbours lives on to the next generation.
        3.  Any live cell with more than three live neighbours dies, as if by overpopulation.
        4.  Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction
    */
    private boolean cellState(boolean cell, int adjCellCount){
        if(cell) {//if cell is alive
            if (adjCellCount < 2 || adjCellCount > 3)
                cell = false;
        }else //if cell is dead
            if (adjCellCount==3)
                cell = true;

        return cell;
    }
    private int wrapValue(int value, int maxValue){
        if(value >= maxValue)
            return value-maxValue;
        else if(value<0)
            return maxValue+value;

        return value;
    }
}
