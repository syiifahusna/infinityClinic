package com.packageindex;

import javax.swing.*;

public abstract class Clinic {

    protected void clearFrame(JFrame frame){
        frame.getContentPane().removeAll();
        frame.repaint();
    }

}
