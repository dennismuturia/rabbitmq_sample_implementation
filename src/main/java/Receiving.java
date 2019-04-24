import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;



public class Receiving {
    //Declare the receiving queue name
    private final static String QUEUE_NAME = "testing";

    public String receiver() throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        final String[] result = {""};

        //Where to get messages from
        connectionFactory.setHost("localhost");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false,false, null);

        System.out.println("Waiting for mesages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            result[0] = message;
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        return result[0];
    }

}
