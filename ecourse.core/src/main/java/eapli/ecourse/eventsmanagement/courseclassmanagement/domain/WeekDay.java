package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

public enum WeekDay {
  SUNDAY, MONDAY, TUESDAY, WEDaNESDAY, THURSDAY, FRIDAY, SATURDAY;

  public static WeekDay valueOf(final int weekDay) {
    switch (weekDay) {
      case 0:
        return SUNDAY;
      case 1:
        return MONDAY;
      case 2:
        return TUESDAY;
      case 3:
        return WEDaNESDAY;
      case 4:
        return THURSDAY;
      case 5:
        return FRIDAY;
      case 6:
        return SATURDAY;
      default:
        throw new IllegalArgumentException("Invalid week day");
    }
  }
}
