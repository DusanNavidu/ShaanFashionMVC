package lk.ijse.gdse72.shaanfashion.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDTO {
    private String userId;
    private String userFullName;
    private String username;
    private String userEmail;
    private String password;
}
