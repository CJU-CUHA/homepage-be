package CUHA.homepage.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Enumerated(EnumType.STRING)
    private Department department;
    private Long score;
    private String password;
    private boolean isActive;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated_at;
}
