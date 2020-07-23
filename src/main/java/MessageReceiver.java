import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.*;

public class MessageReceiver {
    private static String url= ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String queueName = "MESSAGE_QUEUE";

    public static void main(String[] args) throws JMSException, IOException {
        File file=new File("src/NonPersistentFinalFile.txt");
        PrintWriter printWriter=new PrintWriter(file);


        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);
        Connection connection=connectionFactory.createConnection();

        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination=session.createQueue(queueName);
        MessageConsumer consumer=session.createConsumer(destination);
        connection.start();

        long start=System.currentTimeMillis();
        printWriter.write("Start"+"\n");
        for (int i = 0; i <1000 ; i++) {
            Message message=consumer.receive();
            if(message instanceof TextMessage){
                TextMessage textMessage=(TextMessage) message;
                printWriter.write(textMessage.getText()+"\n");
            }
        }
        long stop=System.currentTimeMillis();

        printWriter.write("Time: "+(stop-start));
        printWriter.close();
        connection.close();
    }
}
