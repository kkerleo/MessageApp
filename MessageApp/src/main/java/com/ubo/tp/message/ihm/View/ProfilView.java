package main.java.com.ubo.tp.message.ihm.View;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.controller.AccueilController;
import main.java.com.ubo.tp.message.ihm.controller.ProfilController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProfilView {

    protected AccueilController accueilController;
    protected ProfilController profilController;

    public JPanel ProfilView(ProfilController profilController){
        this.profilController = profilController;
        JPanel userProfilePanel = new JPanel(new BorderLayout());
        userProfilePanel.setPreferredSize(new Dimension(150, 0)); // Set the width of the panel

        JLabel userProfileLabel = new JLabel("Profil :");
        JTextArea userProfileTextArea = new JTextArea();


        User connectedUser = profilController.getConnectedUser();
        if (connectedUser != null) {
            userProfileTextArea.append("Nom : " + connectedUser.getName() + "\n");
            userProfileTextArea.append("Tag : " + connectedUser.getUserTag() + "\n");
            userProfileTextArea.append("Following :" + connectedUser.getFollows().size() + "\n");
            userProfileTextArea.append("People following :" + connectedUser.getFollows() + "\n");
            userProfileTextArea.append("Avatars :" + connectedUser.getAvatarPath() + "\n");
        }

        userProfilePanel.add(userProfileLabel, BorderLayout.NORTH);
        userProfilePanel.add(new JScrollPane(userProfileTextArea), BorderLayout.CENTER);

        JButton deconnexionButton = new JButton("Déconnexion");
        deconnexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profilController.deconnecterUtilisateur();
               // accueilController.destroy();
            }
        });
        userProfilePanel.add(deconnexionButton, BorderLayout.SOUTH);
        return userProfilePanel;
    }
}
