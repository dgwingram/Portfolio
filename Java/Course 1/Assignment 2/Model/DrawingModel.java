/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author DGWIngram
 */
public class DrawingModel implements Serializable{
    ArrayList<LineStrokeModel> lines;
    public DrawingModel() {
        lines = new ArrayList<>();
    }
    public void addLine(LineStrokeModel newLine){
        this.lines.add(newLine);
    }
    public int getNumberLines(){
        return lines.size();
    }
    public LineStrokeModel getLine(int index){
        return lines.get(index);
    }
    
}
