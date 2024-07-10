package sn.faty.Rabbitmq_Project.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQPublisher {

    @Value("${rabbitmq.exchange.name}")

    private String exchangeName;

    @Value("${rabbitmq.binding.routing-key}")

    private  String routing_key;

    private final Logger LOGGER= LoggerFactory.getLogger(RabbitMQPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
      this.rabbitTemplate= rabbitTemplate;
    }

    public  void  sendMessage(String message){

        LOGGER.info(String.format("rabbit message ==>> %s",  message));

        // rabbit transmet le message a lechangeur ce dernier utilise
        // la cle de routage pour lasseminer vers la queue

        rabbitTemplate.convertAndSend(exchangeName,routing_key, message);

    }
}
