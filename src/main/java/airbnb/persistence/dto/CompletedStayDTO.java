package airbnb.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class CompletedStayDTO implements Serializable {
    private String houseName, checkIn, checkOut;
}
