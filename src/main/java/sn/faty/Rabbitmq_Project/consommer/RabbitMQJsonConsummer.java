package sn.faty.Rabbitmq_Project.consommer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sn.faty.Rabbitmq_Project.dto.UserDTO;

@Service
public class RabbitMQJsonConsummer {

    private  final static Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsummer.class);


    public  void consummeMessage(UserDTO userDTO){

        LOGGER.info(String.format("message bien consomme ---->> %s",userDTO.toString()));
    }

}
