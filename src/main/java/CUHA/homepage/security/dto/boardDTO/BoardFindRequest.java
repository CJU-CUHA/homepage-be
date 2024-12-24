package CUHA.homepage.security.dto.boardDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardFindRequest {
    private Long id;
    private String title;
    private String content;
}
