package ru.ifmo.se.kastricyn.lab8.swing;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new AuthForm();
            frame.setVisible(true);
        });
    }
}
