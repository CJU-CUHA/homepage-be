package CUHA.homepage.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileExtension;
    private UUID uuid;
    private String fileLoc;
    private String originalFileName;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
    @CreatedDate
    private LocalDateTime created_at;
}
