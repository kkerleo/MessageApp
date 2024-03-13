package message.ihm.controller;

import message.core.EntityManager;
import message.core.database.IDatabase;
import message.datamodel.User;
import message.ihm.View.ConnexionView;
import message.ihm.View.MessageView;
import message.ihm.View.ProfilView;
import message.ihm.session.ISession;

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
