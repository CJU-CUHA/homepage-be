package CUHA.homepage.security.dto.commentDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CocommentRequest {
    private Long comment_id;
    private String comment;
}
