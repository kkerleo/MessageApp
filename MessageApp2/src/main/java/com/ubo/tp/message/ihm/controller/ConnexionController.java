package main.java.com.ubo.tp.message.ihm.controller;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.Database;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.Session;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

public class ConnexionController {
    protected Database database = new Database();
    protected Session session = new Session();
    protected EntityManager entityManager = new EntityManager(database);

    public void login(String name, String password){

       System.out.println(Arrays.toString(database.getUsers().toArray()));
        entityManager.setExchangeDirectory("src/main/resources/users/");
        User user = new User(UUID.randomUUID(), password, "", name, new HashSet<>(), "");

        if(database.getUsers().contains(user)){
            System.out.println("Connexion réussi");
            session.connect(user);
            session.addObserver(null);
        }
    }
}
