package main.java.com.ubo.tp.message.ihm;

import main.java.com.ubo.tp.message.ihm.controller.ConnexionController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ConnexionView extends JPanel {

    protected JTextField username;
    protected JTextField password;
    protected ConnexionController connexionController = new ConnexionController();

    public ConnexionView() {
        setLayout(new GridBagLayout());

        JLabel usernameLabel = new JLabel("Nom d'utilisateur", SwingConstants.RIGHT);
        add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        JLabel passwordLabel = new JLabel("Tag", SwingConstants.RIGHT);
        add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        username = new JTextField(20);
        add(username, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        password = new JTextField(20);
        add(password, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        JButton connexionButton = new JButton("Se connecter");
        connexionButton.addActionListener(e -> loginOnClick());
        add(connexionButton, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 10, 5), 0, 0));

        // Ajout d'un lien vers la page d'inscription
        JButton inscriptionButton = new JButton("S'inscrire");
        inscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterOnClick();
            }
        });
        add(inscriptionButton, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 5, 10, 0), 0, 0));
    }

    public String getUsernameFieldContent(){
        return username.getText();
    }
    public String getPasswordFieldContent(){
        return password.getText();
    }

    public void loginOnClick(){
        String username = getUsernameFieldContent();
        String password = getPasswordFieldContent();
        connexionController.login(username, password);
    }
    private void RegisterOnClick() {
        this.removeAll();
        this.add(new InscriptionView());
        this.revalidate();
        this.repaint();
    }

}
