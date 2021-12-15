package com.packageindex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    // class constructor
    public Main() {
        // creating the frame
        Frame f = new Frame();
        // creating the label which is final
        final Label label = new Label();

        // setting alignment and size of label
        label.setAlignment(Label.CENTER);
        label.setSize(500, 100);

        // creating a button
        Button b = new Button("Show");

        // setting location of button
        b.setBounds(200, 150, 80, 30);

        // creating the 2 list objects of 4 rows
        // adding items to the list using add()
        // setting location of list components
        final List l1 = new List(4, false);
        l1.setBounds(100, 100, 70, 70);
        l1.add("C");
        l1.add("C++");
        l1.add("Java");
        l1.add("PHP");


        final List l2=new List(4, true);
        l2.setBounds(100, 200, 70, 70);
        l2.add("Turbo C++");
        l2.add("Spring");
        l2.add("Hibernate");
        l2.add("CodeIgniter");

        // adding List, Label and Button to the frame
        f.add(l1);
        f.add(l2);
        f.add(label);
        f.add(b);

        // setting size, layout and visibility of frame
        f.setSize(450,450);
        f.setLayout(null);
        f.setVisible(true);

        // generating event on the button
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String data = "Programming language Selected: "+l1.getItem(l1.getSelectedIndex());
                data += ", Framework Selected:";
                for(String frame:l2.getSelectedItems()) {
                    data += frame + " ";
                }
                label.setText(data);
            }
        });
    }




    public static void main(String[] args) {
        //new Main();
        new HomeView().doctorHome();
    }

}




