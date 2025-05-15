package CUHA.homepage.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Long score;
    private String password;
    private boolean isActive;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated_at;
}
