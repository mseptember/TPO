package zad2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Chat {
	JTextArea poleA;
	JTextArea poleB;
	JButton wyslij;
	JPanel panel;
	JFrame frame;
	
	
	public static void main (String[] args) throws Exception{
		//Chat chat = new Chat();
	}
	
	public Chat() throws Exception {
		TopicSubscriber sub;
		
		Hashtable<String, String> env = new Hashtable<String, String>();
	    env.put(Context.INITIAL_CONTEXT_FACTORY, "org.exolab.jms.jndi.InitialContextFactory");
	    env.put(Context.PROVIDER_URL, "tcp://localhost:3035");
		InitialContext ic = new InitialContext(env);
		
		TopicConnectionFactory factory = (TopicConnectionFactory) ic.lookup("ConnectionFactory");
		Topic topic = (Topic) ic.lookup("topic1");
		TopicConnection con = factory.createTopicConnection();
		TopicSession session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		TopicPublisher publisher = session.createPublisher(topic);
		sub = session.createSubscriber(topic);
		
		 poleA = new JTextArea();
		 poleA.setEditable(false);
		 
		 sub.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message m) {
				try {
					TextMessage message = (TextMessage) m;
					String sender = message.getStringProperty("sender");
					poleA.append(sender + "> " + message.getText() + "/n");
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		 
		 con.start();
		 
		 poleB = new JTextArea();
		 wyslij = new JButton("Wyœlij");
		 wyslij.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = poleB.getText();
				
				if (message.length() > 0) {
					try {
						String sender = System.getProperty("user.name");
						TextMessage m = session.createTextMessage(message);
						m.setStringProperty("sender", sender);
						publisher.publish(m);
						poleB.setText("");
					}
					catch (JMSException ex) {
						ex.printStackTrace();
					}
				}
				
			}
		});
		 
		 panel = new JPanel(new GridLayout(3, 1));
		 poleB.setBorder(BorderFactory.createLineBorder(Color.black));
		 panel.add(poleA);
		 panel.add(poleB);
		 panel.add(wyslij);
		 
		 frame = new JFrame();
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.getContentPane().add(panel);
		 frame.pack();
		 frame.setVisible(true);
		 
		 System.out.println("Sram");
	}
}
