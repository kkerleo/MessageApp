// AccueilController.java
package message.ihm.controller;
import message.datamodel.Message;
import message.datamodel.User;
import message.ihm.View.AccueilView;
import message.core.EntityManager;
import message.core.database.IDatabase;
import message.ihm.View.ConnexionView;
import message.ihm.session.ISession;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class AccueilController {

    protected IDatabase database;
    protected EntityManager entityManager;
    protected ISession session;
    protected AccueilView accueilView;

    public AccueilController(IDatabase database, EntityManager entityManager, ISession mSession) {
        this.database = database;
        this.entityManager = entityManager;
        this.session = mSession;
        this.accueilView = new AccueilView(this);
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
    public ArrayList<Message> recupererMessages() {
        ArrayList<Message> messages = new ArrayList<>(database.getMessages());

        // Trier du plus récent au moins récent
        Collections.sort(messages, (message1, message2) -> Long.compare(message1.getEmissionDate(), message2.getEmissionDate()));

        return messages;
    }
    public ArrayList<Message> rechercherMessage(String searchedMessage) {
        System.out.println("Test recherche messages");
        ArrayList<Message> messages = new ArrayList<>();

        if (!searchedMessage.isEmpty()) {
            if (searchedMessage.startsWith("@")) {
                String userTag = searchedMessage.replace("@", "");
                for (Message message : recupererMessages()) {
                    if (message.getSender().getUserTag().equals(userTag) || message.containsUserTag(searchedMessage)) {
                        messages.add(message);
                    }
                }
            } else if (searchedMessage.startsWith("#")) {
                String userTag = searchedMessage.replace("#", "");
                messages = new ArrayList<>(database.getMessagesWithTag(userTag));
            } else {
                // Recherche par texte
                for (Message message : recupererMessages()) {
                    if (message.containsTag(searchedMessage) || message.containsUserTag(searchedMessage)) {
                        messages.add(message);
                    }
                }
            }
        } else {
            // Si la recherche est vide, récupérer tous les messages
            messages = recupererMessages();
        }
        System.out.println(messages);
        return messages;
    }
    public void ajouterMessage(String message) {
        User connectedUser = getConnectedUser();
        entityManager.writeMessageFile(new Message(connectedUser,message));
        this.accueilView.ajouterMessage(connectedUser,message);
    }
    public User getConnectedUser() {
        return session.getConnectedUser();
    }
    public void deconnecterUtilisateur() {
        session.disconnect();
        ConnexionView co = new ConnexionView();
        new InscriptionController(co,this.database,this.entityManager,this.session);
        new ConnexionController(co,this.database,this.entityManager,this.session);
    }


}
