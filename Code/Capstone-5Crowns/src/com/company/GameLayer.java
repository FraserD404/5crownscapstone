package com.company;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GameLayer extends JFrame implements ActionListener {

    static JFrame frame;
    static JButton b, b1, b2;
    static JLabel lab1;

    public GameLayer(){
        frame = new JFrame("jCrowns");
        lab1 = new JLabel("label");

        b = new JButton("button1");
        b1 = new JButton("button2");
        b2 = new JButton("button3");

        b.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);

        //test = new J
        JPanel panel = new JPanel();

        panel.add(b);
        panel.add(b1);
        panel.add(b2);
        panel.add(lab1);

        panel.setBackground(Color.white);

        frame.add(panel);

        frame.setSize(400,400);

        System.out.println("About to show");

        frame.setVisible(true);

        System.out.println("Shown!");
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == b){
            System.out.println("Button b pressed.");
        }

        if (e.getSource() == b1){
            System.out.println("Button b1 pressed");
        }

        if (e.getSource() == b2){
            System.out.println("Button b2 pressed");
        }
    }
}
