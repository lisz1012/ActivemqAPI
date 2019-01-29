import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    private static final String USERNAME = "cinderella";
    private static final String PASSWORD = "cinderella";
    private static final String ADDRESS = "tcp://localhost";
    private static final String PORT = "61616";

    public static void main (String args[]) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                USERNAME,
                PASSWORD,
                ADDRESS + ":" + PORT);

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("first");
        MessageConsumer consumer = session.createConsumer(destination);

        while (true) {
            Message msg = consumer.receive();
            String text = ((TextMessage)msg).getText();
            System.out.println(text);
        }
    }
}
