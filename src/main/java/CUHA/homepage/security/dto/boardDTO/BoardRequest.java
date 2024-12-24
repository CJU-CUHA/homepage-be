package CUHA.homepage.security.dto.boardDTO;

import CUHA.homepage.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
    private String title;
    private String content;


}
