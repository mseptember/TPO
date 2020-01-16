/**
 * @author Wrzesień Maciej S17390
 */

package zad1;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.nio.channels.*;

public class Client {
    private SocketChannel socketChannel;
    private String nick;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        new LogWindow();
    }

    public Client(String nick) {
        this.start(nick);
    }

    public void start(String nick) {
        this.nick = nick;

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", 9876));
            this.socketChannel = socketChannel;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        ChatWindow cw = new ChatWindow(this);

        while (true) {
            //todo
        }
    }

    public String getNick() {
        return this.nick;
    }

    public void sendMessage (String text) {
        Bufor.writeMessage(getNick() + ":" + text, this.socketChannel);
    }
}

//====================   LogWindow   ======================

class LogWindow extends JPanel implements ActionListener {
    private JFrame frame;
    private JLabel nick;
    private JTextField nickField;
    private JButton ok;

    public LogWindow() {

        frame = new JFrame("LOGIN WINDOW");

        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        frame.setLayout(layout);
        setPreferredSize(new Dimension(300,100));

        nick = new JLabel ("Podaj nick:");
        nickField = new JTextField();
        ok = new JButton("OK");

        nickField.addActionListener(this);
        ok.addActionListener(this);

        add(nick);
        add(nickField);
        add(ok);

        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(nickField.getText().length()>1) {
            new Thread(() -> new Client(nickField.getText())).start();
            frame.dispose();
        }
    }
}
