package CUHA.homepage.security.dto.fileDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardFileSaveRequest {
    private Long id;
    private Long board_id;
}
