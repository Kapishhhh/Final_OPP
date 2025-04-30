package ooop;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginForm implements ActionListener {

    JFrame frame;
    JLabel lblUserName, lblPassword, lblLogin, lblLogo;
    JTextField txtUserName;
    JPasswordField txtPassword;
    JButton btnLogin;
    ImageIcon logoIcon;

    public LoginForm() {
        frame = new JFrame("Turtle Project Form");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK);

        // Panel
        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 420, 400);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        logoIcon = new ImageIcon("C:\\Users\\Kapish\\Desktop\\FinalOOPProject\\src\\ooop\\lbu.jpg"); 
        Image logoImage = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(logoImage);

        // Logo Label
        lblLogo = new JLabel(logoIcon);
        lblLogo.setBounds(10, 10, 50, 50); 

        // Font styles
        Font fontLogin = new Font("SansSerif", Font.BOLD, 30);
        Font font = new Font("SansSerif", Font.PLAIN, 16);

        lblLogin = new JLabel("Turtle Project Form");
        lblLogin.setBounds(80, 20, 350, 50);
        lblLogin.setFont(fontLogin);
        lblLogin.setForeground(Color.WHITE);

        // User Name Label
        lblUserName = new JLabel("User-Name");
        lblUserName.setBounds(50, 100, 100, 30);
        lblUserName.setFont(font);
        lblUserName.setForeground(Color.LIGHT_GRAY);

        // User Name Field
        txtUserName = new JTextField();
        txtUserName.setBounds(160, 100, 180, 30);
        txtUserName.setBackground(Color.DARK_GRAY);
        txtUserName.setForeground(Color.WHITE);
        txtUserName.setCaretColor(Color.WHITE);
        txtUserName.setFont(font);

        // Password Label
        lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 150, 100, 30);
        lblPassword.setFont(font);
        lblPassword.setForeground(Color.LIGHT_GRAY);

        // Password Field
        txtPassword = new JPasswordField();
        txtPassword.setBounds(160, 150, 180, 30);
        txtPassword.setBackground(Color.DARK_GRAY);
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setCaretColor(Color.WHITE);
        txtPassword.setFont(font);

        // Login Button
        btnLogin = new JButton("Login Here");
        btnLogin.setBounds(50, 220, 290, 40);
        btnLogin.setBackground(new Color(98, 82, 219));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(font);
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(this);

        panel.add(lblLogo);
        panel.add(lblLogin);
        panel.add(lblUserName);
        panel.add(txtUserName);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnLogin) {
            User user = new User();
            user.setUserName(txtUserName.getText());
            user.setPassword(txtPassword.getText());

            UserManager userManager = new UserManager();
            boolean result = userManager.login(user);
            if (result) {
                JOptionPane.showMessageDialog(null, "Welcome! Successfully Logged In!");
                frame.dispose();
                GraphicsSystem obj = new GraphicsSystem();
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed! Invalid credentials.");
            }
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}

