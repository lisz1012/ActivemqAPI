import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
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
        MessageProducer producer = session.createProducer(destination);
        //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        for (int i = 0; i < 10; i++) {
            Message msg = session.createTextMessage("Message_" + i);
            producer.send(msg);//, msg, DeliveryMode.PERSISTENT, i, 100000);
            Thread.sleep(1000);
        }

        if (connection != null) {
            connection.close();
        }
    }
}
