package CUHA.homepage.security.dto.userDTO;

import CUHA.homepage.model.Gender;
import CUHA.homepage.model.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String username;
    private String password;
    private Long score;
    private boolean isActive;
    private Gender gender;
    private UserRole userRole;
}
