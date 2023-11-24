package airbnb.persistence.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class UserReviewDTO implements Serializable {
    private int star;
    private String review, userName;

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < star; i++) {
            str.append("*");
        }

        return String.format("[%s]\n%-10s\n%s", userName, str, review);
    }
}
