import javax.jms.Session;

public class Main {

    private static final String URL = "tcp://localhost:61616";
    private static final String QUEUENAME = "MESSAGE_QUEUE";
    private static final boolean ISPERSISTENT = false;
    private static final boolean ISTRANSACTED = false;

    private static final int MODE_ACKNOWLEDGE = Session.AUTO_ACKNOWLEDGE;
    private static final int MODE_TRANSACTED = Session.SESSION_TRANSACTED;
    private static final int MODE_DUPS_OK_ACKNOWLEDGE = Session.DUPS_OK_ACKNOWLEDGE;
    private static final int MODE_CLIENT_ACKNOWLEDGE = Session.CLIENT_ACKNOWLEDGE;

    public static void main(String[] args) {
        MessageSender messageSender = new MessageSender(QUEUENAME, URL, MODE_ACKNOWLEDGE, ISTRANSACTED);
        MessageReceiver messageReceiver = new MessageReceiver(QUEUENAME, URL, MODE_ACKNOWLEDGE, ISTRANSACTED);

        try {
            Broker broker = new Broker(URL, QUEUENAME, ISPERSISTENT, ISTRANSACTED);
            broker.runBroker();

            messageSender.sendMessage(URL, MODE_ACKNOWLEDGE, ISTRANSACTED);
            messageReceiver.receiveMessage(URL, QUEUENAME, MODE_ACKNOWLEDGE, ISTRANSACTED);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
