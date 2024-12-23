package CUHA.homepage.security.dto.userDTO;

import CUHA.homepage.model.Gender;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserjoinRequest {
    private String username;
    private String password;
    private Gender gender;
}