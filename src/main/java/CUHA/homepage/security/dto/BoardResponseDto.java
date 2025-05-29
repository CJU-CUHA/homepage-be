package CUHA.homepage.security.dto;

import CUHA.homepage.domain.Board;
import lombok.*;

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

    public static BoardResponseDto from(Board board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .author(board.getAuthor())
                .content(board.getContent())
                .build();
    }
}
