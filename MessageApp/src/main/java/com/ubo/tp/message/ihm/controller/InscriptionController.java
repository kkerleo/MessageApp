package main.java.com.ubo.tp.message.ihm.controller;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.View.ConnexionView;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class InscriptionController implements ConnexionView.ConnexionListener {

    protected IDatabase database;
    protected EntityManager entityManager;
    protected ISession session;
    protected boolean EstPresent = false;
    protected ConnexionView connexionView;


    public InscriptionController(ConnexionView connexionView, IDatabase database, EntityManager entityManager, ISession session){
        this.connexionView = connexionView;
        connexionView.registerOnClick(this);
        this.database = database;
        this.entityManager = entityManager;
        this.session = session;
    }

    @Override
    public boolean registerOnClick(String username, String tag, String avatar) {
        initFolder();
        boolean valide = false;

        User user = new User(UUID.randomUUID(), tag, "password", username, new HashSet<>(),avatar);

        for(User u : database.getUsers()){
            if(Objects.equals(u.getUserTag(), user.getUserTag())){
                System.out.println("oui");
                EstPresent = true;
                break;
            }
        }

        if(isUsernameNull(username) || isTagNull(tag)) {
            System.out.println("Veuillez remplir les champs d'inscription");
        } else if(EstPresent) {
            System.out.println("Le tag rentré est déjà associé à un compte existant");
        } else {
            database.addUser(user);
            entityManager.writeUserFile(user);
            System.out.println("Inscription réussie, vous pouvez maintenant vous connecter");
            session.connect(user);
            this.connexionView.destroy();
            new AccueilController(database, entityManager, session);
            System.out.println("L'utilisateur " + session.getConnectedUser().getUserTag() + " est connecté!");
            valide = true;
        }
        return valide;
    }


    public boolean isUsernameNull(String username){
        return username == null || username.isEmpty();
    }

    public boolean isTagNull(String tag){
        return tag == null || tag.isEmpty();
    }

    public void initFolder(){
        entityManager.setExchangeDirectory("src/main/resources/users/");
    }

    public Set<User> getUsersRegistered(){
        return database.getUsers();
    }

    @Override
    public boolean loginOnClick(String username, String tag) {
        return false;
    }


}