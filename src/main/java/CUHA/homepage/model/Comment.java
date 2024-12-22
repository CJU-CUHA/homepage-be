package CUHA.homepage.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;
    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;
    private String comment;
    @CreatedDate
    private LocalDateTime created_at;
}
