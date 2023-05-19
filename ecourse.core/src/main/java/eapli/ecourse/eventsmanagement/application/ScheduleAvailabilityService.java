package eapli.ecourse.eventsmanagement.application;

import java.util.ArrayList;
import java.util.List;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.eventsmanagement.classmanagement.repositories.ClassRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ScheduleAvailabilityService {

  private List<SystemUser> systemUsers;
  private ClassRepository classRepository;
  private List<Course> checkedCourses;

  public ScheduleAvailabilityService(ArrayList<SystemUser> systemUsers) {
    this.systemUsers = systemUsers;
  }
}
