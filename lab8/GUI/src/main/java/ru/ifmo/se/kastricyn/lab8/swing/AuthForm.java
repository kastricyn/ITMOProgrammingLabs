package ru.ifmo.se.kastricyn.lab8.swing;

import javax.swing.*;
import java.awt.*;

public class AuthForm {
    private JPanel LogInPanel;
    private JTabbedPane tabbedPane1;
    private JButton войтиButton;
    private JTextField LogInLogIN;
    private JPasswordField PasswordLogIn;
    private JLabel LoginLabel;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    public JPanel getLogInPanel() {
        return LogInPanel;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        LogInPanel = new JPanel();
        LogInPanel.setLayout(new GridBagLayout());
        LogInPanel.putClientProperty("html.disable", Boolean.FALSE);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        LogInPanel.add(panel1, gbc);
        tabbedPane1 = new JTabbedPane();
        tabbedPane1.setTabPlacement(1);
        panel1.add(tabbedPane1);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        tabbedPane1.addTab("Untitled", panel2);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel2.add(panel3, gbc);
        войтиButton = new JButton();
        войтиButton.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(войтиButton, gbc);
        LogInLogIN = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(LogInLogIN, gbc);
        PasswordLogIn = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(PasswordLogIn, gbc);
        LoginLabel = new JLabel();
        LoginLabel.setText("Log In");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(LoginLabel, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(label1, gbc);
    }

    /** @noinspection ALL */
    public JComponent $$$getRootComponent$$$() {
        return LogInPanel;
    }
}
