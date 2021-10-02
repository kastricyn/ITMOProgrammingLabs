package ru.ifmo.se.kastricyn.lab8.client.gui.swing;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(new AuthForm().getLogInPanel());
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
        ru.ifmo.se.kastricyn.lab8.client.console.Main.main(new String[1]);
    }
}
