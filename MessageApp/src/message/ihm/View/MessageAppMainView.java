package message.ihm.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * Classe de la vue principale de l'application.
 */
public class MessageAppMainView {
    protected JFrame mFrame;
    private JLabel fileName = new JLabel();

    public void showGUI() {
        // Init auto de l'IHM au cas ou ;)
        if (mFrame == null) {
            //this.fenetreMenuPopupGUI(); //TODO change call
            this.showGUI();
        }

        // Affichage dans l'EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Custom de l'affichage
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                JFrame frame = mFrame;
                Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((screenSize.width - frame.getWidth()) / 6,
                        (screenSize.height - frame.getHeight()) / 4);

                // Affichage
                mFrame.setVisible(true);

                mFrame.pack();
            }
        });
    }
    protected void setVisible(){
        mFrame.setVisible(true);
    }
}
