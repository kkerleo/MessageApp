package message.ihm.View;
import message.datamodel.Message;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class MessageListRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Message) {
            Message message = (Message) value;
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            String formattedDate = dateFormat.format(message.getEmissionDate());
            String info = formattedDate + " : Utilisateur : " + message.getSender().getUserTag() + " : Message : " + message.getText();
            label.setText(info);
        }

        return label;
    }
}
