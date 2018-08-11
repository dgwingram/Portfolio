/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.DrawingFileFilter;
import model.DrawingModel;
import model.LineStrokeModel;
import view.View;

/**
 *
 * @author DGWIngram
 */
public class Controller
        implements MouseListener, MouseMotionListener,
        ActionListener, ComponentListener, WindowListener, WindowFocusListener, WindowStateListener {

    private final String extension = ".draw";
    int resized = 0;
    View view;
    LineStrokeModel stroke;
    DrawingModel drawingModel;
    boolean drawingCreated = false;

    Color currentColour = Color.BLACK;
    int currentThickness = 1;
    String lastDirectory;
    
    /**
     * Controller
     *
     * @param view
     * @param drawingModel
     * @param lineModel
     */
    public Controller(View view, DrawingModel drawingModel) {
        this.view = view;
        lastDirectory = System.clearProperty("user.home/Document");
        this.drawingModel = drawingModel;
        view.setSize(600, 400);

        // Set Listener
        setupDrawingPanelListeners();

        //Create Panels
        view.addPanel(view.getEastPanel(), BorderLayout.EAST);
        view.addPanel(view.getWestPanel(), BorderLayout.WEST);
        view.addPanel(view.getSouthPanel(), BorderLayout.SOUTH);

        //Create Colour Buttons
        view.addButton(view.getWestPanel(), "BLACK", BorderLayout.WEST, view.getMedBtnSize(), this);
        view.addButton(view.getWestPanel(), "WHITE", BorderLayout.WEST, view.getMedBtnSize(), this);
        view.addButton(view.getWestPanel(), "RED", BorderLayout.WEST, view.getMedBtnSize(), this);
        view.addButton(view.getWestPanel(), "BLUE", BorderLayout.WEST, view.getMedBtnSize(), this);
        view.addButton(view.getWestPanel(), "GREEN", BorderLayout.WEST, view.getMedBtnSize(), this);
        view.addButton(view.getWestPanel(), "ORANGE", BorderLayout.WEST, view.getMedBtnSize(), this);
        view.addButton(view.getWestPanel(), "YELLOW", BorderLayout.WEST, view.getMedBtnSize(), this);
        view.addButton(view.getWestPanel(), "CYAN", BorderLayout.WEST, view.getMedBtnSize(), this);
        view.addButton(view.getWestPanel(), "MAGENTA", BorderLayout.WEST, view.getMedBtnSize(), this);
        view.addButton(view.getWestPanel(), "PINK", BorderLayout.WEST, view.getMedBtnSize(), this);
        view.addButton(view.getWestPanel(), "GRAY", BorderLayout.WEST, view.getMedBtnSize(), this);

        //Create Thickness Buttons
        view.addButton(view.getEastPanel(), "THIN", BorderLayout.EAST, view.getSmallBtnSize(), this);
        view.addButton(view.getEastPanel(), "MEDIUM", BorderLayout.EAST, view.getMedBtnSize(), this);
        view.addButton(view.getEastPanel(), "THICK", BorderLayout.EAST, view.getLargeBtnSize(), this);

        //Create Save/Load Options
        view.addButton(view.getSouthPanel(), "NEW", BorderLayout.NORTH, view.getMedBtnSize(), this);
        view.addButton(view.getSouthPanel(), "SAVE", BorderLayout.NORTH, view.getMedBtnSize(), this);
        view.addButton(view.getSouthPanel(), "OPEN", BorderLayout.NORTH, view.getMedBtnSize(), this);

        //Set Panel Colours
        //        add(view.getScrollPane());
        // Show Screen
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Controller() {
    }

    private void setupDrawingPanelListeners() {
        view.getDrawingPanel().addMouseListener(this);
        view.getDrawingPanel().addMouseMotionListener(this);
        view.getDrawingPanel().addComponentListener(this);
        view.addWindowListener(this);
        view.addWindowFocusListener(this);
        //view.getDrawingPanel().setBackground(Color.WHITE);
    }

    private void redraw() {
        for (int i = 0; i < this.drawingModel.getNumberLines(); i++) {
            stroke = drawingModel.getLine(i);

            view.drawPoints(stroke.getPoints(), stroke.getColour(), stroke.getThickness());
        }

        //view.drawPoints(stroke.getPoints(),this.currentColour,this.currentThickness);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.stroke = new LineStrokeModel(this.currentColour, this.currentThickness);
        Point pt = e.getPoint();
        view.setCurrentPosition(pt);
        stroke.addPoint(pt);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point pt = e.getPoint();
        view.addPoint(pt, this.currentColour, this.currentThickness);
        stroke.addPoint(pt);
        this.drawingModel.addLine(stroke);

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point pt = e.getPoint();
        view.addPoint(pt, this.currentColour, this.currentThickness);
        stroke.addPoint(pt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color colour = checkColour(e.getActionCommand());
        if (colour != null) {
            this.currentColour = colour;

        } else {
            switch (e.getActionCommand()) {
                case "THIN":
                    this.currentThickness = 1;
                    break;
                case "MEDIUM":
                    this.currentThickness = 5;
                    break;
                case "THICK":
                    this.currentThickness = 10;
                    break;
                case "SAVE":
                    saveDrawing();
                    break;
                case "OPEN":
                    open();
                    break;
                case "NEW":
                    newDrawing();
            }
        }
    }

    private Color checkColour(String name) {
        Color colour;
        try {
            Field field = Class.forName("java.awt.Color").getField(name);
            colour = (Color) field.get(null);

        } catch (Exception e) {
            colour = null; // Not defined
        }
        return colour;
    }

    private void newDrawing() {
        this.drawingModel = new DrawingModel();
        view.resetDrawingPanel(view.getGraphics());
    }

    public void saveDrawing() {

        JFileChooser fc = new JFileChooser(this.lastDirectory);
        fc.setFileFilter(new DrawingFileFilter(this.extension, "MyDrawing File"));
        int result = fc.showSaveDialog(null);
        fc.getCurrentDirectory();
        switch (result) {
            case JFileChooser.APPROVE_OPTION:

                try {
                    FileOutputStream outfs = null;

                    try {
                        String filename = fc.getSelectedFile().getAbsoluteFile().toString();
                        
                        System.out.println(filename.lastIndexOf(this.extension));
                        if (filename.lastIndexOf(filename) == -1) {
                            outfs = new FileOutputStream(fc.getSelectedFile().getAbsoluteFile());
                        } else {
                            outfs = new FileOutputStream(fc.getSelectedFile().getAbsoluteFile() + ".draw");
                        }

                        ObjectOutputStream outStream = new ObjectOutputStream(outfs);
                        outStream.writeObject(this.drawingModel);
                        this.lastDirectory =fc.getCurrentDirectory().toString();

                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(),"Saving Error",JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(),"Saving Error",JOptionPane.ERROR_MESSAGE);
                    } finally {
                        try {
                            outfs.close();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(),"Saving Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(),"Saving Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
            case JFileChooser.CANCEL_OPTION:
                break;
            case JFileChooser.ERROR_OPTION:
                JOptionPane.showMessageDialog(new JFrame(), "Error Saving File","Saving Error",JOptionPane.ERROR_MESSAGE);
                this.lastDirectory="user.home/Document";
                break;
        }
    }

    public void open() {

        JFileChooser fc = new JFileChooser(this.lastDirectory);
        fc.setFileFilter(new DrawingFileFilter(".draw", "MyDrawing File"));
        int result = fc.showOpenDialog(null);
        switch (result) {
            case JFileChooser.APPROVE_OPTION:
                try {
                    // ArrayList<LineStrokeModel> loadedDrawing=null;
                    FileInputStream infs = new FileInputStream(fc.getSelectedFile().getAbsoluteFile());
                    ObjectInputStream inputStream = new ObjectInputStream(infs);
                    
                    // Clear Screen
                    newDrawing();
                    //Save Loaded Model
                    this.drawingModel = (DrawingModel) inputStream.readObject();
                    //ReDraw on screen
                    redraw();
                    this.lastDirectory=fc.getCurrentDirectory().toString();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(),"Open Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
            case JFileChooser.CANCEL_OPTION:
                break;
            case JFileChooser.ERROR_OPTION:
                JOptionPane.showMessageDialog(new JFrame(), "Error Saving File","Open Error",JOptionPane.ERROR_MESSAGE);
                this.lastDirectory="user.home/Document";
                break;
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        redraw();

    }

    @Override
    public void componentShown(ComponentEvent e) {
        redraw();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        redraw();

    }

    @Override
    public void windowOpened(WindowEvent e) {
        redraw();
    }

    @Override
    public void windowIconified(WindowEvent e) {
        redraw();
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        redraw();
    }

    @Override
    public void windowActivated(WindowEvent e) {
        redraw();

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        redraw();
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        redraw();

    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        redraw();
    }

    @Override
    public void windowStateChanged(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }
}
