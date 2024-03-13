// AccueilView.java
package message.ihm.View;

import message.datamodel.Message;
import message.datamodel.User;
import message.ihm.controller.AccueilController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AccueilView {
    protected JFrame mFrame;
    protected JList<Message> messageList;
    protected DefaultListModel<Message> messageListModel;
    protected JList<String> userList;
    protected DefaultListModel<String> userListModel;
    protected AccueilController accueilController;
    protected JTextArea userProfileTextArea;

    public AccueilView(AccueilController controller) {
        this.accueilController = controller;
        fenetreMenuPopupGUI();
    }

    private void fenetreMenuPopupGUI() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        Dimension dimension = new Dimension(1100, 550);
        ImageIcon imageIcon = new ImageIcon("src/main/resources/images/logo_20.png");

        this.mFrame = new JFrame("Accueil");
        this.mFrame.setLayout(new BorderLayout());
        this.mFrame.setPreferredSize(dimension);
        this.mFrame.setIconImage(imageIcon.getImage());

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fichierMenu = new JMenu("Fichier");
        JMenu autreMenu = new JMenu("?");

        JMenuItem ouvrirMenuItem = new JMenuItem("Ouvrir");
        ImageIcon openIcon = new ImageIcon("src/main/resources/images/editIcon_20.png");
        ouvrirMenuItem.setIcon(openIcon);
        ouvrirMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecteurFichier();
            }
        });
        JMenuItem quitterMenuItem = new JMenuItem("Quitter");
        ImageIcon crossIcon = new ImageIcon("src/main/resources/images/exitIcon_20.png");
        quitterMenuItem.setIcon(crossIcon);
        quitterMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fichierMenu.add(ouvrirMenuItem);
        fichierMenu.add(quitterMenuItem);

        JFrame finalMFrame = mFrame;
        autreMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ImageIcon imageIcon = new ImageIcon("src/main/resources/images/logo_50.png");
                JLabel jLabel = new JLabel("<html><center>UBO M2-TIIL<br>Département Informatique");
                JOptionPane.showMessageDialog(finalMFrame, jLabel, "A propos", JOptionPane.INFORMATION_MESSAGE, imageIcon);
            }
        });
        menuBar.add(fichierMenu);
        menuBar.add(autreMenu);
        this.mFrame.setJMenuBar(menuBar);
        JPanel userProfilePanel = new JPanel(new BorderLayout());
        userProfilePanel.setPreferredSize(new Dimension(200, 0));

        JLabel userProfileLabel = new JLabel("Profil :");
        this.userProfileTextArea = new JTextArea();
        User connectedUser = accueilController.getConnectedUser();
        if (connectedUser != null) {
            ImageIcon imageIcon2 = new ImageIcon(connectedUser.getAvatarPath());
            userProfileLabel.setIcon(imageIcon2);
            userProfileTextArea.append("Nom : " + connectedUser.getName() + "\n");
            userProfileTextArea.append("Tag : " + connectedUser.getUserTag() + "\n");
            userProfileTextArea.append("Following :" + connectedUser.getFollows().size() + "\n");
            userProfileTextArea.append("Follows :" + connectedUser.getFollows() + "\n");

        }
        userProfilePanel.add(userProfileLabel, BorderLayout.NORTH);
        userProfilePanel.add(new JScrollPane(userProfileTextArea), BorderLayout.CENTER);

        JButton deconnexionButton = new JButton("Déconnexion");
        ImageIcon image = new ImageIcon("src/main/resources/images/exitIcon_20.png");
        deconnexionButton.setIcon(image);
        deconnexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accueilController.deconnecterUtilisateur();
                mFrame.dispose();
                mFrame.getContentPane().removeAll();
                mFrame.revalidate();
                mFrame.repaint();
            }
        });
        userProfilePanel.add(deconnexionButton, BorderLayout.SOUTH);
        this.mFrame.add(userProfilePanel, BorderLayout.WEST);
        ArrayList<String> utilisateurs = accueilController.recupererUtilisateurs();
        userListModel = new DefaultListModel<String>();
        for (String user : utilisateurs) {
            userListModel.addElement(user);
        }
        userList = new JList<>(userListModel);
        JScrollPane userScrollPane = new JScrollPane(userList);
        // Message List
        ArrayList<Message> arrayList = accueilController.recupererMessages();
        messageListModel = new DefaultListModel<Message>();
        for (Message message : arrayList) {
            messageListModel.addElement(message);
        }
        messageList = new JList<>(messageListModel);
        messageList.setCellRenderer(new MessageListRenderer());
        JScrollPane messageScrollPane = new JScrollPane(messageList);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, userScrollPane, messageScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        JTextField rechercheTextField = new JTextField(20);
        JButton rechercheButton = new JButton("Rechercher");
        imageIcon = new ImageIcon("src/main/resources/images/Search.png");
        rechercheButton.setIcon(imageIcon);
        rechercheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rechercheText = rechercheTextField.getText();
                ArrayList<String> a = accueilController.rechercherUtilisateur(rechercheText);
                userListModel.clear();
                userList.clearSelection();
                // Ajouter les utilisateurs trouvés à la liste dans la vue
                for (String user : a) {
                    userListModel.addElement(user);
                }
                userList.setModel(userListModel);
            }
        });
        JTextField rechercheMessTextField = new JTextField(20);
        JButton rechercheMessButton = new JButton("Rechercher");
        imageIcon = new ImageIcon("src/main/resources/images/Search.png");
        rechercheMessButton.setIcon(imageIcon);

        rechercheMessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rechercheText = rechercheMessTextField.getText();
                ArrayList<Message> a = accueilController.rechercherMessage(rechercheText);
                messageListModel.clear();
                messageList.clearSelection();
                for (Message mess : a) {
                    messageListModel.addElement(mess);
                }
                messageList.setModel(messageListModel);
            }
        });
        JPanel recherchePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        recherchePanel.add(new JLabel("Rechercher Utilisateur: "));
        recherchePanel.add(rechercheTextField);
        recherchePanel.add(rechercheButton);
        recherchePanel.add(new JLabel("Rechercher Messages: "));
        recherchePanel.add(rechercheMessTextField);
        recherchePanel.add(rechercheMessButton);

        JButton sabonnerButton = new JButton("S'abonner");
        sabonnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String utilisateurSelectionne = userList.getSelectedValue();
                if (utilisateurSelectionne != null && !utilisateurSelectionne.equals(connectedUser.getUserTag())) {
                    accueilController.suivreUtilisateur(utilisateurSelectionne);
                    RafraichirUser();
                } else {
                    JOptionPane.showMessageDialog(mFrame, "Veuillez sélectionner un autre utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton desabonnerButton = new JButton("Se désabonner");
        desabonnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String utilisateurSelectionne = userList.getSelectedValue();
                if (utilisateurSelectionne != null) {
                    accueilController.pasSuivreUtilisateur(utilisateurSelectionne);
                    RafraichirUser();
                } else {
                    JOptionPane.showMessageDialog(mFrame, "Veuillez sélectionner un utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton ajouterMessageButton = new JButton("Ajouter Message");
        imageIcon = new ImageIcon("src/main/resources/images/Message.png");
        ajouterMessageButton.setIcon(imageIcon);
        ajouterMessageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nouveauMessage = demanderNouveauMessage();
                if(nouveauMessage != null){
                    if(nouveauMessage.length()<200){
                        accueilController.ajouterMessage(nouveauMessage);
                    }else {
                        JOptionPane.showMessageDialog(mFrame, "Message non compatible (supérieur à 200)", "Erreur", JOptionPane.ERROR_MESSAGE);
                        nouveauMessage = demanderNouveauMessage();
                    }
                }
            }
        });
        messageList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = messageList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String selectedMessage = messageListModel.getElementAt(selectedIndex).getText();
                        String selectedUser = messageListModel.getElementAt(selectedIndex).getSender().getUserTag();
                        Date date = new Date(messageListModel.getElementAt(selectedIndex).getEmissionDate());
                        String avatar = messageListModel.getElementAt(selectedIndex).getSender().getAvatarPath();
                        afficherPopUpMessage(selectedUser,date, selectedMessage,avatar);
                    }
                }
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 5));
        buttonPanel.add(sabonnerButton);
        buttonPanel.add(desabonnerButton);
        buttonPanel.add(ajouterMessageButton);

        this.mFrame.add(recherchePanel, BorderLayout.NORTH);
        this.mFrame.add(splitPane, BorderLayout.CENTER);
        this.mFrame.add(buttonPanel, BorderLayout.SOUTH);

        this.mFrame.pack();
        this.mFrame.setLocationRelativeTo(null);
        this.mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mFrame.setVisible(true);
    }
    public void RafraichirUser(){
        userProfileTextArea.setText("");
        userProfileTextArea.append("Nom : " + this.accueilController.getConnectedUser().getName() + "\n");
        userProfileTextArea.append("Tag : " + this.accueilController.getConnectedUser().getUserTag() + "\n");
        userProfileTextArea.append("Following :" + this.accueilController.getConnectedUser().getFollows().size() + "\n");
        userProfileTextArea.append("Follows :" + this.accueilController.getConnectedUser().getFollows() + "\n");
    }
    public void ajouterMessage(User sender, String message) {
        Message newmessage = new Message(sender,message);
        messageListModel.addElement(newmessage);
        accueilController.recupererMessages();
    }

    private String demanderNouveauMessage() {
        return JOptionPane.showInputDialog(mFrame, "Veuillez saisir votre nouveau message :", "Ajouter Message", JOptionPane.PLAIN_MESSAGE);
    }
    private void afficherPopUpMessage(String utilisateur, Date date, String message,String avatar) {
        User user = accueilController.getConnectedUser();
        ImageIcon imageIcon = new ImageIcon(avatar);

        // Utilisation de SimpleDateFormat pour formater la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = dateFormat.format(date);

        JLabel jLabel = new JLabel("<html><b>Heure :</b> " + formattedDate +
                "<br><b>Utilisateur :</b> " +  utilisateur + "<br><b>Message:</b> " + message);

        JOptionPane.showMessageDialog(mFrame, jLabel, "Détails du Message", JOptionPane.INFORMATION_MESSAGE, imageIcon);
    }
    protected void selecteurFichier() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Ouvrir");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(mFrame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Fichier sélectionné : " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("Aucun fichier sélectionné");
        }
    }


}
