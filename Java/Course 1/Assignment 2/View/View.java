/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author dingram20
 */
public class View extends JFrame {

    JPanel drawingPanel;
    Point currentPosition;
    Color backgroundColour = Color.WHITE;
    // Create Panels
    private JPanel westPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    //Define button Sized
    private Dimension sidePanelSize = new Dimension(200, 400);
    private Dimension northSize = new Dimension(200, 400);
    private Dimension smallBtn = new Dimension(100, 17);
    private Dimension mediumBtn = new Dimension(100, 22);
    private Dimension largeBtn = new Dimension(100, 30);
    private Dimension dmTxtArea = new Dimension(400, 45);

    BorderLayout layout;

    public View(String title) {
        super(title);

        drawingPanel = new JPanel();
        drawingPanel.setBackground(Color.decode("#f1f5f7"));
        add(drawingPanel, BorderLayout.CENTER);

    }

    public void resetDrawingPanel(Graphics graphics) {

        super.update(graphics);/*
            super.toBack();
            super.toFront();/*
            drawingPanel = null;
            drawingPanel = new JPanel();
            drawingPanel.setBackground(Color.decode("#f1f5f7"));
            add(drawingPanel, BorderLayout.CENTER);*/

    }

    public JPanel getDrawingPanel() {
        return drawingPanel;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void addPoint(Point pt, Color colour, int thickness) {

        Graphics2D graphics = (Graphics2D) drawingPanel.getGraphics();
        graphics.setStroke(new BasicStroke(thickness));
        graphics.setColor(colour);
        graphics.drawLine(
                currentPosition.x, currentPosition.y,
                pt.x, pt.y);
        currentPosition = pt;
    }

    public void drawPoints(ArrayList<Point> ptList, Color colour, int thickness) {
        Point pt1, pt2;
        Graphics2D graphics = (Graphics2D) drawingPanel.getGraphics();

        graphics.setStroke(new BasicStroke(thickness));
        graphics.setColor(colour);

        for (int p = 1; p < ptList.size(); p++) {
            pt1 = ptList.get(p - 1);
            pt2 = ptList.get(p);
            graphics.drawLine(pt1.x, pt1.y, pt2.x, pt2.y);
        }
        graphics.dispose();
    }

    public void addButton(JPanel panel, String name, String location, Dimension size, ActionListener listener) {
        JButton button = new JButton(name);
        button.addActionListener(listener);
        button.setMaximumSize(size);
        panel.add(button);
    }

    public void addPanel(JPanel panel, String location) {
        //panel.setBackground(colour);
        panel.setBackground(Color.decode("#c6d5ff"));
        if (location == BorderLayout.NORTH || location == BorderLayout.SOUTH) {
            panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        } else {
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        }

        add(panel, location);
    }

    public JPanel getWestPanel() {
        return westPanel;
    }

    public JPanel getEastPanel() {
        return eastPanel;
    }

    public JPanel getSouthPanel() {
        return southPanel;
    }

    //public void addPanel(JPanel panel, )
    /**
     *
     * @return size of Thin button
     */
    public Dimension getSmallBtnSize() {
        return smallBtn;
    }

    /**
     *
     * @return normal button size
     */
    public Dimension getMedBtnSize() {
        return mediumBtn;
    }

    /**
     *
     * @return Thick button size
     */
    public Dimension getLargeBtnSize() {
        return largeBtn;
    }

    public void drawPoints(ArrayList<Point> points) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void createInnerPanel(JPanel panel) {
        panel.setLayout(layout);

    }
}
