package CUHA.homepage.security.dto;

import CUHA.homepage.domain.Board;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BoardResponseDto from(Board board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .author(board.getAuthor().getId())
                .content(board.getContent())
                .like(board.getLike())
                .dislike(board.getDislike())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}
