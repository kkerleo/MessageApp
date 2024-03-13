package message.ihm.View;

import message.datamodel.User;
import message.ihm.controller.AccueilController;
import message.ihm.controller.UserController;

import javax.swing.*;
import java.util.ArrayList;

public class UserView {
    protected JList<String> userList;
    protected DefaultListModel<String> userListModel;
    public UserView(UserController accueilController){
        ArrayList<String> utilisateurs = accueilController.recupererUtilisateurs();
        userListModel = new DefaultListModel<String>();
        for (String user : utilisateurs) {
            userListModel.addElement(user);
        }
        userList = new JList<>(userListModel);
    }
    public void AjouterUser(String user){
        userListModel.addElement(user);
    }
    public void ClearUser(){
        this.userList.clearSelection();
        this.userListModel.clear();
    }
}
