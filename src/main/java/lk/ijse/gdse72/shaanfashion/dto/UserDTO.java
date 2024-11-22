package lk.ijse.gdse72.shaanfashion.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class UserDTO {
    private String userId;
    private String userFullName;
    private String username;
    private String userEmail;
    private String password;
}
