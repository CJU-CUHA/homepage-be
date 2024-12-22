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
public class ExamFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileExtension;
    private UUID uuid;
    private String fileLoc;
    private String originalFileName;

    @ManyToOne
    @JoinColumn(name="exam_id")
    private Exam exam;
    @CreatedDate
    private LocalDateTime created_at;
}
