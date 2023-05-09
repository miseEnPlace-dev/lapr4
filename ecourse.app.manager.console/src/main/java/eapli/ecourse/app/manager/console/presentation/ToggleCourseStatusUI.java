package eapli.ecourse.app.manager.console.presentation;

import eapli.ecourse.coursemanagement.application.ToggleCourseStatusController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.framework.actions.Action;
import eapli.framework.io.util.Console;

import java.util.List;
import java.util.Scanner;

public class ToggleCourseStatusUI implements Action {

  private ToggleCourseStatusController ctrl = new ToggleCourseStatusController();

  @Override
  public boolean execute() {

    System.out.println("\n[1] - Open Course");
    System.out.println("[2] - Close Course");
    System.out.println("[0] - Exit");

    int option = Console.readOption(1, 2, 0);

    if (option == 0)
      return true;

    Iterable<Course> courses;
    if (option == 1) {
      courses = ctrl.listClosedCourses();
    } else {
      courses = ctrl.listOpenCourses();
    }

    int itr = 1;
    for (Course course: courses) {
      System.out.print(itr + "." + "\n");
      System.out.println(course.title());
      System.out.println(course.code());
      System.out.println(course.state());
    }

    int choice = 0;
    while (choice < 1 || choice > itr) {
      choice = Console.readInteger("\nSelect a Course: ");
    }

    Course course = ((List<Course>) courses).get(choice);

    String confirm = "";
    System.out.println("Do you want to confirm the toggle of the Course Status? [Y/N]");

    while (!confirm.equals("Y") && !confirm.equals("N")) {
      confirm = Console.readLine("Option: ").toUpperCase();
    }

    if (confirm.equals("Y")) {
      ctrl.toggleCourseStatus(course);
      System.out.println("Course Status successfully changed");
    } else {
      System.out.println("Operation Cancelled");
    }

    Scanner scanner = new Scanner(System.in);
    System.out.println("\nPress enter to continue...");
    scanner.nextLine();

    return true;
  }
}
