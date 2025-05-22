package CUHA.homepage.security.dto;

import CUHA.homepage.domain.Gender;
import CUHA.homepage.domain.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinDto {
    private String email;
    private String username;
    private String password;
    private UserRole userRole;
    private Gender gender;
}
