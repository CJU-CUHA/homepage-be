package CUHA.homepage.security.dto.fileDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamFileSaveRequest {
    private Long id;
    private Long exam_id;
}
