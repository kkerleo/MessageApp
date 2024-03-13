package main.java.com.ubo.tp.message.ihm.controller;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.View.MessageView;
import main.java.com.ubo.tp.message.ihm.View.UserView;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import java.util.ArrayList;

public class UserController {
    protected UserView userView;
    protected IDatabase database;
    protected EntityManager entityManager;
    protected ISession session;



    public UserController(IDatabase database, EntityManager entityManager, ISession mSession) {
        this.database = database;
        this.entityManager = entityManager;
        this.session = mSession;
        this.userView = new UserView(this);
    }
    public ArrayList<String> recupererUtilisateurs() {
        ArrayList<String> array = new ArrayList<String>();
        for (User user : database.getUsers()) {
            array.add(user.getUserTag());
        }
        return array;
    }
    public ArrayList<String> rechercherUtilisateur(String rechercheText) {
        ArrayList<String> utilisateursTrouves = new ArrayList<>();

        if (rechercheText.isEmpty()) {
            // Recherche vide, récupérer tous les utilisateurs
            utilisateursTrouves.addAll(this.recupererUtilisateurs());
        } else {
            // Recherche non vide, filtrer les utilisateurs
            for (User user : database.getUsers()) {
                if (user.getUserTag().contains(rechercheText) || user.getName().contains(rechercheText)) {
                    utilisateursTrouves.add(user.getUserTag());
                }
            }
        }

        return utilisateursTrouves;
    }

    public void suivreUtilisateur(String utilisateur) {
        User connectedUser = getConnectedUser();
        if (connectedUser != null) {
            connectedUser.addFollowing(utilisateur);
            entityManager.writeUserFile(connectedUser);
        }
    }
    public void pasSuivreUtilisateur(String utilisateur) {
        User connectedUser = getConnectedUser();
        if (connectedUser != null) {
            connectedUser.removeFollowing(utilisateur);
            entityManager.writeUserFile(connectedUser);
        }
    }
    public User getConnectedUser() {
        return session.getConnectedUser();
    }
}
