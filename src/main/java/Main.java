import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Main {
    //Start the queue name that we will be sending the messages
    private final static String QUEUE_NAME = "testing";
    public  static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()){
            channel.queueDeclare(QUEUE_NAME, false,false,false,null);
            String message = "Hello World";

            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            System.out.println("[x] Sent'" + message +"'");




            //Set to receive
            Receiving receiving = new Receiving();
            receiving.receiver();
        }
    }
}
