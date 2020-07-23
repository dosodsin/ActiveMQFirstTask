import org.apache.activemq.broker.BrokerService;

public class Broker {
    public static void main(String[] args) throws Exception {
        BrokerService brokerService=new BrokerService();
        brokerService.addConnector("http://localhost:8161");
        brokerService.start();
    }
}
