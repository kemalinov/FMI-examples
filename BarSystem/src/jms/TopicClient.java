package jms;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

public class TopicClient {

    private static Logger log = Logger.getLogger(TopicClient.class.getName());

    @Resource(mappedName = "jms/TopicFactory")
    private static ConnectionFactory topicFactory;

    @Resource(mappedName = "jms/Topic")
    private static Topic topic;

    public static void sendMessage() {
	Connection topicConnection = null;
	Session session = null;
	MapMessage message = null;
	MessageProducer producer = null;

	try {
	    topicConnection = topicFactory.createConnection();
	    session = topicConnection.createSession(false,
		    Session.AUTO_ACKNOWLEDGE);

	    producer = session.createProducer(topic);

	    message = session.createMapMessage();
	    message.setString("lastname", "Smith");
	    message.setString("firstname", "John");
	    message.setString("id", "0100");

	    producer.send(message);

	    System.out.println("sent a message");
	} catch (Exception e) {
	    log.log(Level.SEVERE, "Topic client exception: ", e);
	}
    }
}
