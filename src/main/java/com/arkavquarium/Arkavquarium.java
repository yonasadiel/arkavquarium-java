package com.arkavquarium;

import com.arkavquarium.controllers.*;
import com.arkavquarium.models.Data;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

public class Arkavquarium {
    
    public static JFrame f = new JFrame("Arkavquarium");

    public static void main(String[] args) {
        Aquarium test = new Aquarium();
        f.add(test);
        test.main(1);    
        f.setSize(Data.getMaxWidth(), Data.getMaxHeight());    
        f.setVisible(true);
    }
}
