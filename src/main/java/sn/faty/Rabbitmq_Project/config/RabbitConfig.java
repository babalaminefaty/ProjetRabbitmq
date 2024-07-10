package sn.faty.Rabbitmq_Project.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.queue.name}")

    private String queueName;

    @Value("${rabbitmq.queue.json.name}")

    private String jsonQueueName;

    @Value("${rabbitmq.exchange.name}")

    private String exchangeName;

    @Value("${rabbitmq.exchange.json.name}")

    private String jsonExchangeName;

    @Value("${rabbitmq.binding.routing-key}")

    private  String routing_key;


    @Value("${rabbitmq.binding.json.routing-key}")

    private  String jsonRouting_key;


  @Bean
    public Queue queue() {

        return new Queue(queueName);
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueueName);
    }


    @Bean
    public Exchange jsonExchange(){

        return new TopicExchange(exchangeName);
  }

    @Bean
    public Exchange JSonExchange(){

        return new TopicExchange(exchangeName);
    }


    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue()).to(jsonExchange())
                .with(jsonRouting_key).noargs();
    }


    // Fonction convertisseur : Transforme les Objets JAVA en JSON
    //  lors de l'emission
    // et lors de la consommation JSON est ensuite transforme en objet JAVA
    @Bean
     public MessageConverter messageConverter(){

      return new Jackson2JsonMessageConverter();
     }

     //Connexion sur RabbitMQ et sassurer que le message est ransforme en JSON
   @Bean
    public  AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){

      RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

      rabbitTemplate.setMessageConverter(messageConverter());

       return rabbitTemplate;

    }

}
