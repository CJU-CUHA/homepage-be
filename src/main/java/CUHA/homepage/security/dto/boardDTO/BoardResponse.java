package CUHA.homepage.security.dto.boardDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
}
