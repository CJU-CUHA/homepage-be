package CUHA.homepage.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardReactionResponseDto {
    private Long boardId;
    private Long likeCount;
    private Long dislikeCount;
    private String userReaction;
}
