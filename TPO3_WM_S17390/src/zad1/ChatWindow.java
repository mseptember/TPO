package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JPanel implements ActionListener {
    private JFrame frame;
    private JTextArea wiadomosc;
    private JTextArea chatArea;
    private JButton wyloguj;
    private Client client;


    public ChatWindow(Client client) {
        this.client = client;

        frame = new JFrame("CHAT WINDOW");

        BorderLayout layout = new BorderLayout(1,1);

        frame.setLayout(layout);
        setPreferredSize(new Dimension(400,400));

        chatArea = new JTextArea(10, 10);
        wiadomosc = new JTextArea(3,10);

        wyloguj = new JButton("Wyloguj");
        wyloguj.setActionCommand("wyloguj");
        wyloguj.addActionListener(this);

        wiadomosc.setLineWrap(true);
        chatArea.setLineWrap(true);
        chatArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(chatArea);

        add(wyloguj);
        add(scrollPane);
        add(wiadomosc);

        setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("logout".equals(e.getActionCommand())) System.exit(0);
        if(wiadomosc.getText().length() > 0)
            //this.client.sendMessage(wiadomosc.getText());
        wiadomosc.setText("");
    }

    public void addMessage(String text) { // dodaje wiadomość do Chatu
        chatArea.append(text + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength()); // ustawia karetkę w "ostatnim" punkcie okna
        chatArea.update(chatArea.getGraphics()); // "odświeża"
    }
}
