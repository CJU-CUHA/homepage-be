package CUHA.homepage.security.dto.fileDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamFileResponse {
    private Long id;
    private String fileName;
}
