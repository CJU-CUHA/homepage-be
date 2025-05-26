package CUHA.homepage.security.dto;

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
}
