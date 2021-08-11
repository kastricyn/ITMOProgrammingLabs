package ru.ifmo.se.kastricyn.lab8.swing;

import javax.swing.*;
import java.awt.*;

public class AuthForm extends JFrame {
    {
        setTitle("Вход");
        setLayout(new GridLayout(3, 1));

// ввод логина
        JTextField login = new JTextField(20);
        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.add(new JLabel("Логин:", SwingConstants.RIGHT), BorderLayout.WEST);
        loginPanel.add(login, BorderLayout.EAST);
        add(loginPanel);
//ввод пароля
        JPasswordField password = new JPasswordField(20);
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.add(new JLabel("Пароль:", SwingConstants.RIGHT), BorderLayout.WEST);
        passwordPanel.add(password, BorderLayout.EAST);
        add(passwordPanel);

//кнопка входа/регистрации
        AuthRegisterButton arButton = new AuthRegisterButton();
        arButton.setAuth();
        add(arButton, BorderLayout.SOUTH);


        // Верхняя менюшка
//todo: добавим горячие клавиши
        JMenu authMenu = new JMenu("Вход");
        JMenu registerMenu = new JMenu("Регистрация");
        JMenu languageMenu = new JMenu("Язык");
        JMenuItem aboutMenuItem = new JMenuItem("О программе");
        JMenu helpMenu = new JMenu("Помощь");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(authMenu).setEnabled(false);
        menuBar.add(registerMenu);
        menuBar.add(languageMenu);
        menuBar.add(helpMenu).add(aboutMenuItem);
        setJMenuBar(menuBar);

// обработка доступности входа при выборе в менюшки
        authMenu.addItemListener(e -> {
            authMenu.setEnabled(false);
            registerMenu.setEnabled(true);
            arButton.setAuth();
        });

// обработка доступности регистрации при выборе в менюшки
        registerMenu.addItemListener(e -> {
            authMenu.setEnabled(true);
            registerMenu.setEnabled(false);
            arButton.setRegister();
        });

        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a new, initially invisible <code>Frame</code> with the
     * specified title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param title the title for the frame
     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     *                           returns true.
     * @see GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public AuthForm(String title) throws HeadlessException {
        super(title);
    }

    /**
     * Constructs a new frame that is initially invisible.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     *                           returns true.
     * @see GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public AuthForm() throws HeadlessException {
    }

    public void ViewAuth() {

    }

    public void RegisterAuth() {

    }

    public static class AuthRegisterButton extends JButton {
        private boolean register = false;

        public boolean isAuth() {
            return !register;
        }

        public boolean isRegister() {
            return register;
        }

        public void setRegister() {
            register = true;
            setText("Зарегистрироваться");
        }

        public void setAuth() {
            register = false;
            setText("Войти");
        }
    }


}
