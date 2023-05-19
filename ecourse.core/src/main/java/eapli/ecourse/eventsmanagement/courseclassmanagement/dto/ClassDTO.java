package eapli.ecourse.eventsmanagement.courseclassmanagement.dto;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ClassID;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.DayInWeek;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.Hours;
import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassDTO {
  private ClassID id;
  private DayInWeek dayInWeek;
  private Duration duration;
  private Hours hours;
  private Course course;
}
