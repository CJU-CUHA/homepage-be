package CUHA.homepage.security.dto.fileDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralFileResponse {
    private Long id;
    private String filename;
}
