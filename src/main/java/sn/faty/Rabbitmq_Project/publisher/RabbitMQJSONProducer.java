package sn.faty.Rabbitmq_Project.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sn.faty.Rabbitmq_Project.dto.UserDTO;

@Service
public class RabbitMQJSONProducer {


    private  final static Logger LOGGER = LoggerFactory.getLogger(RabbitMQJSONProducer.class);

    private final RabbitTemplate rabbitTemplate;


    @Value("${rabbitmq.queue.json.name}")

    private String jsonQueueName;


    @Value("${rabbitmq.exchange.json.name}")

    private String jsonExchangeName;

    @Value("${rabbitmq.binding.json.routing-key}")

    private String jsonRoutingKey;

    @Autowired
    public RabbitMQJSONProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJSonMessage(UserDTO userDTO) {

        LOGGER.info(String.format("#### Sending message to ##### %s", userDTO.toString()));

        rabbitTemplate.convertAndSend(jsonExchangeName, jsonRoutingKey,userDTO);
    }

}
