import org.apache.activemq.broker.BrokerService;

public class Broker {

    private String url;
    private String queueName;
    private boolean isPersistent;
    private boolean isTransacted;

    public Broker(String url, String queueName, boolean isPersistent, boolean isTransacted) {
        this.url = url;
        this.queueName = queueName;
        this.isPersistent = isPersistent;
        this.isTransacted = isTransacted;
    }

    public void runBroker() {
        BrokerService brokerService = new BrokerService();

        try {
            brokerService.addConnector(url);
            brokerService.setPersistent(isPersistent);
            brokerService.setUseJmx(false);
            brokerService.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
