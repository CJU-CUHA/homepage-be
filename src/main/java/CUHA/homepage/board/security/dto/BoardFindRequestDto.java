package CUHA.homepage.board.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardFindRequestDto {
    private Long id;
    private String title;
    private String content;
}
