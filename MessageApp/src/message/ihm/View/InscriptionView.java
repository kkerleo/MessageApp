package message.ihm.View;

import message.ihm.controller.InscriptionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InscriptionView extends JPanel {

    protected JFrame mframe;
    protected JTextField username;
    protected JTextField tag;
    protected JTextField avatar;
    protected InscriptionController inscriptionController;
    protected JButton connexionButton;
    protected JButton inscriptionButton;
    protected InscriptionListener inscriptionListener;

    public InscriptionView(JFrame mFrame) {
        this.mframe = mFrame;

        ImageIcon imageIcon = new ImageIcon("src/main/resources/images/logo_20.png");

        mFrame.setTitle("Inscription");  // Correction du titre
        mFrame.setSize(400, 300);
        mFrame.setLocationRelativeTo(null);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mFrame.setLayout(new GridBagLayout());

        mFrame.setIconImage(imageIcon.getImage());

        JPanel inscriptionPanel = new JPanel();  // Correction du nom de la variable
        inscriptionPanel.setLayout(new GridBagLayout());

        setLayout(new GridBagLayout());

        JLabel usernameLabel = new JLabel("Nom d'utilisateur", SwingConstants.RIGHT);
        inscriptionPanel.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel tagLabel = new JLabel("Tag", SwingConstants.RIGHT);
        inscriptionPanel.add(tagLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        username = new JTextField(20);
        inscriptionPanel.add(username, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        tag = new JTextField(20);
        inscriptionPanel.add(tag, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        inscriptionButton = new JButton("S'inscrire");  // Correction du texte du bouton
        inscriptionPanel.add(inscriptionButton, new GridBagConstraints(0, 2, 2, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        connexionButton = new JButton("Se connecter");
        inscriptionPanel.add(connexionButton, new GridBagConstraints(0, 3, 2, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        mFrame.add(inscriptionPanel);  // Correction de la variable utilisée
        mFrame.setVisible(true);
    }

    public void RegisterOnClick(InscriptionController inscriptionController) {
        inscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inscriptionListener != null) {
                    inscriptionListener.registerOnClick(username.getText(), tag.getText());
                }
            }
        });
    }

    public void loginOnClick() {
        connexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mframe.getContentPane().removeAll();
                mframe.add(new InscriptionView(mframe));
                mframe.revalidate();
                mframe.repaint();
            }
        });
    }


    public void setInscriptionListener(InscriptionListener listener) {
        this.inscriptionListener = listener;
    }

    public interface InscriptionListener {
        void registerOnClick(String username, String tag);
    }
}
