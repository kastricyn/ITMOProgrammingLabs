package ru.ifmo.se.kastricyn.lab8.swing;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hello");
            JLabel label = new JLabel("");
            JButton button = new JButton("OK");
            frame.getContentPane().setLayout(new FlowLayout());
            frame.getContentPane().add(label);
            frame.getContentPane().add(button);
            button.addActionListener((ae) ->
            {
                label.setText("Привет!");
            });
            frame.setSize(240, 120);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
