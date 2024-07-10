package sn.faty.Rabbitmq_Project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private int id;
    private String firstName;
    private String lastName ;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
