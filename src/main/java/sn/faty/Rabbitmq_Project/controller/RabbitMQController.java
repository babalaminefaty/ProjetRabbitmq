package sn.faty.Rabbitmq_Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.faty.Rabbitmq_Project.consommer.RabbitMQJsonConsummer;
import sn.faty.Rabbitmq_Project.dto.UserDTO;
import sn.faty.Rabbitmq_Project.publisher.RabbitMQJSONProducer;
import sn.faty.Rabbitmq_Project.publisher.RabbitMQPublisher;



@RestController
@RequestMapping("/api/v1")

public class RabbitMQController {

    private final RabbitMQPublisher rabbitMQPublisher;

    private final RabbitMQJSONProducer rabbitMQJSONProducer;
    private final RabbitMQJsonConsummer rabbitMQJsonConsummer;

    @Autowired
    public RabbitMQController(RabbitMQPublisher rabbitMQPublisher, RabbitMQJSONProducer rabbitMQJSONProducer, RabbitMQJsonConsummer rabbitMQJsonConsummer) {
        this.rabbitMQPublisher=rabbitMQPublisher;
        this.rabbitMQJSONProducer=rabbitMQJSONProducer;
        this.rabbitMQJsonConsummer = rabbitMQJsonConsummer;
    }

    // http:localhost:8080/api/v1/publisher?message=hello Word
    @GetMapping("/publisher")
    public ResponseEntity<?> publish(@RequestParam("message") String message) {

        rabbitMQPublisher.sendMessage(message);


        return  ResponseEntity.ok("Message Published");
    }


    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody UserDTO userDTO) {

        rabbitMQJSONProducer.sendJSonMessage(userDTO);

        rabbitMQJsonConsummer.consummeMessage(userDTO);

        return  ResponseEntity.ok("Message Publied Successfully");
    }



}
