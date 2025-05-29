package CUHA.homepage.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardReactionRequestDto {
    private Long boardId;
    private String userReaction;
}
