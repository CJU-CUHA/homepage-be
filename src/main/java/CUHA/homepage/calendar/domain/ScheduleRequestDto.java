package CUHA.homepage.calendar.domain;

import CUHA.homepage.calendar.util.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDto {
    Long id;
    LocalDate date;
    Title title;
    String subtitle;
    Long userId;
}
