package message.ihm.View;

import message.datamodel.Message;
import message.datamodel.User;
import message.ihm.controller.AccueilController;
import message.ihm.controller.MessageController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MessageView {
    private final JFrame mFrame;
    protected MessageController messageController;
    protected AccueilController accueilController;
    protected JList<Message> messageList;
    protected DefaultListModel<Message> messageListModel;

    public MessageView(MessageController messageController,JFrame mFrame) {
        this.mFrame = mFrame;
        this.messageController = messageController;
        ArrayList<Message> arrayList = messageController.recupererMessages();
        messageListModel = new DefaultListModel<Message>();
        for (Message message : arrayList) {
            messageListModel.addElement(message);
        }
        messageList = new JList<>(messageListModel);
        messageList.setCellRenderer(new MessageListRenderer());
        JScrollPane messageScrollPane = new JScrollPane(messageList);
        messageList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = messageList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String selectedMessage = messageListModel.getElementAt(selectedIndex).getText();
                        String selectedUser = messageListModel.getElementAt(selectedIndex).getSender().getUserTag(); // Vous pouvez ajuster cela selon votre logique
                        Date date = new Date(messageListModel.getElementAt(selectedIndex).getEmissionDate());
                        afficherPopUpMessage(selectedUser,date, selectedMessage);
                    }
                }
            }
        });
    }
    private void afficherPopUpMessage(String utilisateur, Date date, String message) {
        User user = messageController.getConnectedUser();
        ImageIcon imageIcon = new ImageIcon(user.getAvatarPath());

        // Utilisation de SimpleDateFormat pour formater la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = dateFormat.format(date);

        // Utilisation de HTML pour la mise en forme du texte dans le JLabel
        JLabel jLabel = new JLabel("<html><b>Heure :</b> " + formattedDate +
                "<br><b>Utilisateur :</b> " +  utilisateur + "<br><b>Message:</b> " + message);

        JOptionPane.showMessageDialog(mFrame, jLabel, "Détails du Message", JOptionPane.INFORMATION_MESSAGE, imageIcon);
    }
    public void ajouterMessage(User sender, String message) {
        Message newmessage = new Message(sender,message);
        messageListModel.addElement(newmessage);
    }
    public void ClearMessage(){
        messageListModel.clear();
        messageList.clearSelection();
    }
    public String demanderNouveauMessage() {
        return JOptionPane.showInputDialog(mFrame, "Veuillez saisir votre nouveau message :", "Ajouter Message", JOptionPane.PLAIN_MESSAGE);
    }
}
