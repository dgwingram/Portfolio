/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author cbanger
 */
public class AppFrame extends JFrame {
    
    BorderLayout layout = new BorderLayout();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    DrawingPanel drawingPanel = new DrawingPanel();
    JScrollPane scrollPane = new JScrollPane();
    JTextArea logText = new JTextArea();
    Dimension width;
    public AppFrame()  {
        super("COMP 305 Lab 4 Demo");
        int setBtnWidth = 300;
        
        getContentPane().setLayout(layout);
        
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        
        drawingPanel.setBackground(Color.WHITE);
        add(drawingPanel, BorderLayout.CENTER);
        
        add(scrollPane, BorderLayout.SOUTH);
        
        drawingPanel.addMouseListener(drawingPanel);
        drawingPanel.addMouseMotionListener(drawingPanel);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setPreferredSize(new Dimension(100, 400));
        
        JButton btnBlue=new JButton("Blue"); // unless you need to refer to the same button, no need to use abother button
        btnBlue.addActionListener(drawingPanel);
        leftPanel.add(btnBlue);
        btnBlue.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnBlue.getMinimumSize().height));
        
        
        JButton btnRed=new JButton("Red"); 
        btnRed.addActionListener(drawingPanel);
        leftPanel.add(btnRed);
        btnRed.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnRed.getMinimumSize().height));
        
        JButton btnGreen=new JButton("Green"); // unless you need to refer to the same button, no need to use abother button
        btnGreen.addActionListener(drawingPanel);
        leftPanel.add(btnGreen);
        btnGreen.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnGreen.getMinimumSize().height));
          JButton btnMedium=new JButton("Medium");
        btnMedium.addActionListener(drawingPanel);
        btnMedium.setMaximumSize(width);
        btnMedium.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnMedium.getMinimumSize().height));
        
        
        JButton btnThin=new JButton("Thin");
        btnThin.addActionListener(drawingPanel);
        btnThin.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThin.getMinimumSize().height));

        JButton btnThick=new JButton("Thick");
        btnThick.addActionListener(drawingPanel);
        btnThick.setMaximumSize(width);
        btnThick.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThin.getMinimumSize().height));
                
        rightPanel.add(btnThin);
        rightPanel.add(btnMedium);
        rightPanel.add(btnThick);
    }}
