package main.java.com.ubo.tp.message.ihm.controller;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.View.ConnexionView;
import main.java.com.ubo.tp.message.ihm.View.MessageView;
import main.java.com.ubo.tp.message.ihm.View.ProfilView;
import main.java.com.ubo.tp.message.ihm.session.ISession;

public class ProfilController {
    protected ProfilView profilView;
    protected IDatabase database;
    protected EntityManager entityManager;
    protected ISession session;


    public ProfilController(IDatabase database, EntityManager entityManager, ISession mSession) {
        this.database = database;
        this.entityManager = entityManager;
        this.session = mSession;
        this.profilView = new ProfilView();
    }
    public void deconnecterUtilisateur() {
        session.disconnect();
        ConnexionView co = new ConnexionView();
        new InscriptionController(co,this.database,this.entityManager,this.session);
        new ConnexionController(co,this.database,this.entityManager,this.session);
    }
    public User getConnectedUser() {
        return session.getConnectedUser();
    }
}
