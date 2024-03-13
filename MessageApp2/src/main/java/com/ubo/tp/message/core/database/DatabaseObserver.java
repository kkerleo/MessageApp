package main.java.com.ubo.tp.message.core.database;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;

public class DatabaseObserver implements IDatabaseObserver {

    @Override
    public void notifyMessageAdded(Message addedMessage) {
        System.out.println("Message ajouté");
    }

    @Override
    public void notifyMessageDeleted(Message deletedMessage) {
        System.out.println("Message supprimé");

    }

    @Override
    public void notifyMessageModified(Message modifiedMessage) {
        System.out.println("Message modifié");

    }

    @Override
    public void notifyUserAdded(User addedUser) {
        System.out.println("Utilisateur ajouté");

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {
        System.out.println("Utilisateur supprimé");

    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        System.out.println("Utilisateur modifié");

    }
}
