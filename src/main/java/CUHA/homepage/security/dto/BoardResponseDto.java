package CUHA.homepage.security.dto;

import CUHA.homepage.domain.Board;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BoardResponseDto {
    private Long id;
    private String title;
    private Long author;
    private String content;
    private Long like;
    private Long dislike;
    private String userReaction;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BoardResponseDto from(Board board) {
        return from(board, null);
    }

    public static BoardResponseDto from(Board board, String userReaction) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .author(board.getAuthor().getId())
                .content(board.getContent())
                .like(board.getLike())
                .dislike(board.getDislike())
                .userReaction(userReaction)
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}
