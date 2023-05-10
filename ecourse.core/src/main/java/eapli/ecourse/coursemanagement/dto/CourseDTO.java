package eapli.ecourse.coursemanagement.dto;

import java.util.Calendar;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
  private CourseCode code;
  private CourseTitle title;
  private CourseDescription description;
  private EnrolmentLimits enrolmentLimits;
  private CourseState courseState;
  private CourseEnrolmentState enrolmentState;
  private Calendar createdAt;
}
