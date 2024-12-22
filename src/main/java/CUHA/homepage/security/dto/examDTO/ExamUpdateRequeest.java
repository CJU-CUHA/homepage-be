package CUHA.homepage.security.dto.examDTO;

import CUHA.homepage.model.Category;
import CUHA.homepage.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamUpdateRequeest {
    private Long id;
    private String title;
    private String content;
    private String answer;
    private Long score;
    private Category category;
}
