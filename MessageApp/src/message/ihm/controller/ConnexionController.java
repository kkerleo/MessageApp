package message.ihm.controller;

import message.core.EntityManager;
import message.core.database.IDatabase;
import message.datamodel.User;
import message.ihm.View.ConnexionView;
import message.ihm.session.ISession;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

public class ConnexionController implements ConnexionView.ConnexionListener {
    protected IDatabase database;
    protected EntityManager entityManager;
    protected ISession session;

    protected ConnexionView connexionView;
    protected boolean doUserExists = false;

    public ConnexionController(ConnexionView connexionView, IDatabase database, EntityManager entityManager, ISession mSession) {
        this.connexionView = connexionView;
        connexionView.loginOnClick(this);
        this.database = database;
        this.entityManager = entityManager;
        this.session = mSession;
    }

    public boolean loginOnClick(String username, String tag) {
        initFolder();
        User Utilisateur = null;

        boolean EstPresent = false;
        for(User user : database.getUsers()){
            if(Objects.equals(user.getUserTag(), tag)){
                Utilisateur = user;
                EstPresent = true;
                break;
            }
        }

        if(EstPresent){
            session.connect(Utilisateur);
            this.connexionView.destroy();
            new AccueilController(database, entityManager, session);

        } else {
            EstPresent = false;
        }
    return EstPresent;
    }

    @Override
    public boolean registerOnClick(String username, String tag, String text) {
        return false;
    }

    public User userFromUsernameAndPassword(String username, String tag){
        return new User(UUID.randomUUID(), tag, "",username, new HashSet<>(), "");
    }

    public void initFolder(){
        entityManager.setExchangeDirectory("src/main/resources/users/");
    }

}
