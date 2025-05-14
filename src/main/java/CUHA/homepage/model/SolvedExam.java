package CUHA.homepage.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolvedExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User username;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    private boolean solved;


}
