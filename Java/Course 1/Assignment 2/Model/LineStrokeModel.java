/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author DGWIngram
 */
public class LineStrokeModel implements Serializable{
    
    ArrayList<Point> points = new ArrayList<>();
    private Color colour; 
    private int thickness; 

    public LineStrokeModel() {
        this.colour=Color.BLACK;
        this.thickness=1;
    }
     
    public LineStrokeModel(Color colour, int thickness) {
        this.colour = colour;
        this.thickness = thickness;
    }
    
    public ArrayList<Point> getPoints() {
        return points;
    }
    public void addPoint(Point pt)
    {
        points.add(pt);
    }

    public Color getColour(){
        return this.colour;
    }
    public int getThickness(){
        return this.thickness;
    }   
}
