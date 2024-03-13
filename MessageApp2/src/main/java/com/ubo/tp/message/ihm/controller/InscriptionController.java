package main.java.com.ubo.tp.message.ihm.controller;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.Database;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

public class InscriptionController {
    protected IDatabase database = new Database();
    protected EntityManager entityManager = new EntityManager(database);

   /* public InscriptionController(Database data, EntityManager entity){
        database = data;
        entityManager = entity;
    }*/
    public void register(String name, String password, String avatar){
        System.out.println(Arrays.toString(database.getUsers().toArray()));

        User user = new User(UUID.randomUUID(), password, "", name, new HashSet<>(), avatar);
        database.addUser(user);
        entityManager.setExchangeDirectory("src/main/resources/users/");
        entityManager.writeUserFile(user);

        System.out.println(Arrays.toString(database.getUsers().toArray()));
    }
}
