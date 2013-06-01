//package jms;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import javax.annotation.Resource;
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.MessageDriven;
//import javax.ejb.Startup;
//import javax.ejb.Stateless;
//import javax.jms.ConnectionFactory;
//import javax.jms.MapMessage;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.Topic;
//
//@MessageDriven(mappedName = "jms/Topic", activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
//		@ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"), @ActivationConfigProperty(propertyName = "clientId", propertyValue = "MessageBeanBean"),
//		@ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "MessageBeanBean") })
//public class MessageBeanBean implements MessageListener {
//
//	private static Logger log = Logger.getLogger(MessageBeanBean.class.getName());
//
//	@Resource(mappedName = "jms/TopicFactory")
//	private ConnectionFactory topicFactory;
//	@Resource(mappedName = "jms/Topic")
//	private Topic topic;
//
//	public MessageBeanBean() {
//	}
//
//	@Override
//	public void onMessage(Message message) {
//		MapMessage msg = null;
//		System.out.println("received message");
//		try {
//
//			msg = (MapMessage) message;
//			System.out.println("—————————————");
//			System.out.println(msg.getString("lastname"));
//			System.out.println(msg.getString("firstname"));
//			System.out.println(msg.getString("id"));
//			System.out.println("—————————————");
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "MessageBean exception: ", e);
//		}
//	}
//
//}
