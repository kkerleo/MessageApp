package message.ihm.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ConnexionView extends JPanel {

    protected JFrame jFrame = new JFrame();
    protected JTextField username;
    protected JTextField tag;
    protected String avatar;
    protected JLabel messageLabel;
    protected JButton connexionButton;
    protected JButton inscriptionButton;
    protected JButton avatarButton;

    public ConnexionView() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            throw new RuntimeException(ex);
        }
        ImageIcon imageIcon = new ImageIcon("src/main/resources/images/logo_20.png");
        jFrame.setTitle("Connexion/Inscription");
        jFrame.setIconImage(imageIcon.getImage());
        jFrame.setSize(500, 300);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new GridBagLayout());

        JPanel connexionPanel = new JPanel();
        connexionPanel.setLayout(new GridBagLayout());
        connexionPanel.setBorder(BorderFactory.createTitledBorder("Connexion/Inscription"));
        connexionPanel.setBackground(new Color(240, 240, 240));

        JLabel usernameLabel = new JLabel("Nom d'utilisateur", SwingConstants.RIGHT);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        connexionPanel.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(10, 10, 0, 10), 0, 0));

        JLabel tagLabel = new JLabel("Tag", SwingConstants.RIGHT);
        tagLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        connexionPanel.add(tagLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(10, 10, 0, 10), 0, 0));

        JLabel avatarLabel = new JLabel("Avatar", SwingConstants.RIGHT);
        avatarLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        connexionPanel.add(avatarLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(10, 10, 0, 10), 0, 0));

        username = new JTextField(20);
        connexionPanel.add(username, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(10, 0, 0, 10), 0, 0));

        tag = new JTextField(20);
        connexionPanel.add(tag, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(10, 0, 0, 10), 0, 0));

        avatarButton = new JButton("Selection de l'avatar");
        connexionPanel.add(avatarButton, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(10, 0, 0, 10), 0, 0));
        avatarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valeur = selecteurFichier();
                if(!valeur.isEmpty()) {
                    avatar = valeur;
                }
            }
        });
        connexionButton = new JButton("Se connecter");
        ImageIcon connectIcon = new ImageIcon("src/main/resources/images/connexion.png");
        connexionButton.setIcon(connectIcon);
        connexionButton.setBackground(new Color(0, 102, 204));
        connexionButton.setForeground(Color.BLACK);
        connexionButton.setFont(new Font("Arial", Font.BOLD, 14));
        connexionPanel.add(connexionButton, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(10, 10, 0, 5), 0, 0));

        inscriptionButton = new JButton("S'inscrire");
        ImageIcon registerIcon = new ImageIcon("src/main/resources/images/inscription.png");
        inscriptionButton.setIcon(registerIcon);
        inscriptionButton.setBackground(new Color(0, 102, 204));
        inscriptionButton.setForeground(Color.BLACK);
        inscriptionButton.setFont(new Font("Arial", Font.BOLD, 14));
        connexionPanel.add(inscriptionButton, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(10, 5, 0, 10), 0, 0));


        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        connexionPanel.add(messageLabel, new GridBagConstraints(0, 4, 2, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(10, 10, 0, 10), 0, 0));

        jFrame.add(connexionPanel);
        jFrame.setVisible(true);
    }
    public void loginOnClick(ConnexionListener connexionListener) {
        connexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean val = connexionListener.loginOnClick(username.getText(), tag.getText());
                if(val){
                    JOptionPane.showMessageDialog(jFrame, "Bienvenue !", "Connexion", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(jFrame, "Utilisateur inexistant", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void registerOnClick(ConnexionListener connexionListener) {
        System.out.println("Test");
        inscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               boolean val = connexionListener.registerOnClick(username.getText(), tag.getText(), avatar);
                if(val){
                    JOptionPane.showMessageDialog(jFrame, "Inscription valide", "Inscription", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(jFrame, "Inscription invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void destroy() {
        jFrame.dispose();
        jFrame.getContentPane().removeAll();
        jFrame.revalidate();
        jFrame.repaint();
    }
    protected String selecteurFichier() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Ouvrir");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this.jFrame);
        String val = "";
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Fichier sélectionné : " + selectedFile.getAbsolutePath());
            val = selectedFile.getAbsolutePath();
        } else {
            val = "";
            System.out.println("Aucun fichier sélectionné");
        }
        return val;
    }

    public interface ConnexionListener {
        boolean loginOnClick(String username, String tag);

        boolean registerOnClick(String username, String tag, String text);
    }
}
