package CUHA.homepage.security.dto.userDTO;

import CUHA.homepage.model.Gender;
import CUHA.homepage.model.UserRole;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFindResponse {
    private String username;
    private Long score;
    private boolean isActive;
    private Gender gender;
    private UserRole userRole;
    private LocalDateTime created_at;
    private String message;
}
