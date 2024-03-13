package main.java.com.ubo.tp.message.ihm.controller;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.View.AccueilView;
import main.java.com.ubo.tp.message.ihm.View.MessageView;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import javax.swing.*;
import java.util.ArrayList;

public class MessageController {
    protected IDatabase database;
    protected EntityManager entityManager;
    protected ISession session;
    protected MessageView messageView;


    public MessageController(JFrame mFrame, IDatabase database, EntityManager entityManager, ISession mSession) {
        this.database = database;
        this.entityManager = entityManager;
        this.session = mSession;
        this.messageView = new MessageView(this,mFrame);
    }
    public ArrayList<Message> recupererMessages() {
        return new ArrayList<>(database.getMessages());
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
        messageView.ajouterMessage(connectedUser,message);
    }
    public User getConnectedUser() {
        return session.getConnectedUser();
    }

}
