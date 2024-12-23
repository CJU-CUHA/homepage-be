package CUHA.homepage.security.dto.userDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {
    private String message;
    private boolean success;
}
