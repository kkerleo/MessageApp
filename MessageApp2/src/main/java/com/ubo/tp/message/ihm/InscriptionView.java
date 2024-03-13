package main.java.com.ubo.tp.message.ihm;

import main.java.com.ubo.tp.message.ihm.controller.InscriptionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class InscriptionView extends JPanel {

    protected JTextField username;
    protected JTextField password;
    protected JTextField avatar;
    protected InscriptionController inscriptionController = new InscriptionController();
    public InscriptionView() {
        setLayout(new GridBagLayout());

        JLabel usernameLabel = new JLabel("Nom d'utilisateur", SwingConstants.RIGHT);
        add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        JLabel passwordLabel = new JLabel("Tag", SwingConstants.RIGHT);
        add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        JLabel avatarLabel = new JLabel("Avatar", SwingConstants.RIGHT);
        add(avatarLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        username = new JTextField(20);
        add(username, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        password = new JTextField(20);
        add(password, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        avatar = new JTextField(20);
        add(avatar, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        JButton inscriptionButton = new JButton("S'inscrire");
        inscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterOnClick();
            }
        });
        add(inscriptionButton, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 10, 5), 0, 0));

        JButton loginButton = new JButton("Se connecter");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginOnClick();
            }
        });
        add(loginButton, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 5, 10, 0), 0, 0));
    }


    public String getUsernameFieldContent(){
        return username.getText();
    }

    public String getPasswordFieldContent(){
        return password.getText();
    }

    public String getAvatarFieldContent(){
        return avatar.getText();
    }

    public void RegisterOnClick(){
        String username = getUsernameFieldContent();
        String password = getPasswordFieldContent();
        String avatar = getAvatarFieldContent();
        inscriptionController.register(username, password, avatar);
    }
    public void LoginOnClick(){
        this.removeAll();
        this.add(new ConnexionView());
        this.revalidate();
        this.repaint();
    }
}
