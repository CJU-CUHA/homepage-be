package CUHA.homepage.security.dto.examDTO;

import CUHA.homepage.model.Category;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamUpdateRequest {
    private Long id;
    private String title;
    private String content;
    private String answer;
    private Long score;
    private Category category;
}
