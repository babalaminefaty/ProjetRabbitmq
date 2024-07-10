package sn.faty.Rabbitmq_Project.consommer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsummer {


    @RabbitListener(queues = "${rabbitmq.queue.name}")

    public  void consumer(String message){

        System.out.println(message + "***********************");

    }
}
